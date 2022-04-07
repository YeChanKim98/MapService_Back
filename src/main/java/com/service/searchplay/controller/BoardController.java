package com.service.searchplay.controller;


import com.service.searchplay.service.SimpleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {

    private final SimpleReviewService simpleReviewService;

    @Autowired
    public BoardController(SimpleReviewService simpleReviewService) {
        this.simpleReviewService = simpleReviewService;
    }

    // 자유게시판 메인
    @RequestMapping("")
    public String freeBoardMain(@PathVariable(required=false) Optional<Integer> page, Model model){


        if(page.isEmpty()){page= Optional.of(1);} // 페이지 미선택은 기본으로 1값
        int[] pageInfo = pagination(page.get(),  -1); // 페이지네이션 정보
        if(page.get()>pageInfo[0]){
            page = Optional.of(pageInfo[0]); // 최고페이지 이상으로 가려고하면 강제로 마지막페이지로 이동
            pageInfo[1] = page.get();
        }
        List<notice_Board> noticeList = boardService.listNoticeView().subList(0,5);
        model.addAttribute("noticeList",noticeList);
        List<free_Board> boardList = boardService.mainView(page.get()); // 마지막 페이지보다 높은 페이지 요구하면 강제로 마지막 페이지 반환
        model.addAttribute("boardList",boardList);
        model.addAttribute("from","main");
        model.addAttribute("page_now",page.get());
        model.addAttribute("page_total",pageInfo[0]);
        model.addAttribute("block_start",pageInfo[1]);
        model.addAttribute("block_end",pageInfo[2]);

        return "boards/free/FreeBoardMain"; // 메인페이지로 이동}
    }

    // 작성(삽입)
    @GetMapping("freeBoard/write")
    public String freeWrite(Model model){
        System.out.println("새 글 작성 요청");
        model.addAttribute("use","write");
        return "boards/WriteForm";
    }

    @PostMapping("freeBoard/write")
    public String writePost(@RequestParam(required = false)String writer_option, HttpServletRequest request, WriteForm writeForm){ // 리덱터리 리스팅 회피 : writer변수와 세션에서 받은 변수의 값이 일치하지 않으면 메인으로 리다이렉트
        System.out.println("글작성");
        free_Board board = new free_Board();
        HttpSession session = request.getSession();
        if(session.getAttribute("loginID")==null) {
            board.setFboard_writer("익명(비로그인)");
        }else if(writer_option!=null){
            board.setFboard_writer("익명(로그인)");
        }else{
            board.setFboard_writer(session.getAttribute("loginID").toString());
        }
        board.setFboard_title(writeForm.getFboard_title());
        board.setFboard_content(writeForm.getFboard_content());
        boardService.writeBoard(board);
        return "forward:/freeBoard/view/main/1"; // 리다이렉트시, 받는곳과 현재 내 메서드가 달라서 새창으로 감. 이를 위해서 받는 곳에서 Post를 받을 수 있게하고 포워드로 보냄
    }

    // 삭제
    @GetMapping("{kinds}/delete/{num}")
    public String freeDelete(@PathVariable String kinds,@PathVariable int num, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
            if(kinds.equals("freeBoard")) {
                boardService.deleteBoard(num);
                commentService.deleteComment(num);
                out.println("<script>alert('게시글 삭제 완료');location.href='/freeBoard/view/main/1';</script>");
                out.flush();
                out.close();
            }
        if(kinds.equals("notice")) {
            boardService.deleteNotice(num);
            return "redirect:/freeBoard/view/main/1";
        }
        return "redirect:/freeBoard/view/main/1";
    }

    // 수정
    @GetMapping("{kinds}/update/{id}/{num}")
    public String boardUpdate(@PathVariable String kinds,@PathVariable String id, @PathVariable int num, Model model, HttpServletRequest request){
        if(kinds.equals("freeBoard")) {
            if(request.getSession().getAttribute("loginID").equals(id)||request.getSession().getAttribute("loginID").equals("Admin")) {
                free_Board board = boardService.viewBoard_free(num); // 뷰가 아닌 하나 가져오는 코드 필요 : 자주 쓰임
                model.addAttribute("boardInfo", board);
                model.addAttribute("kinds", kinds);
                model.addAttribute("use", "update");
            }else{
                System.out.println("[BoardController][boardUpdate] 글 수정은 본인만 가능합니다 : 작성자 - "+id+" / 현재 로그인 : "+request.getSession().getAttribute("loginID"));
                return "redirect:/";
            }
        }else if(kinds.equals("notice")){
            if(request.getSession().getAttribute("loginID").equals(id)) {
                notice_Board board = boardService.viewBoard_notice(num);
                model.addAttribute("boardInfo", board);
                model.addAttribute("kinds", kinds);
                model.addAttribute("use", "update");
            }
            else return "redirect:/";
        }else System.out.println("[BoardController][boardUpdate] 게시판 식별에 실패했습니다");
        System.out.println("작성폼으로 연결");
        return "boards/WriteForm";
    }

    @PostMapping("{kinds}/update/{id}/{num}")
    public String boardUpdate(@PathVariable String kinds, WriteForm writeForm, HttpServletRequest request){
        System.out.println("게시글 수정을 시작합니다");
        System.out.println("메소드 : "+request.getPathInfo());
        if(kinds.equals("freeBoard")){
            boardService.updateBoard(writeForm,kinds);
        }else if(kinds.equals("notice")){
            boardService.updateBoard(writeForm,kinds);
        }
        return "redirect:/freeBoard/view/main";
    }


    // 조회
    @GetMapping("{kinds}/view/{num}")
    public String viewBoard(@PathVariable String kinds, @PathVariable int num, Model model){
        if(kinds.equals("freeBoard")){
            System.out.println("[viewBoard][freeBoard] 서비스 호출");
            free_Board post = boardService.viewBoard_free(num);
            model.addAttribute("num",post.getFboard_num());
            model.addAttribute("writer",post.getFboard_writer());
            model.addAttribute("title",post.getFboard_title());
            model.addAttribute("content",post.getFboard_content());
            model.addAttribute("view_cnt",post.getFboard_view_count());
            model.addAttribute("comment_cnt",post.getFboard_comment_count());
            model.addAttribute("reg_date",post.getFboard_reg_date());
            model.addAttribute("from_kinds",kinds);
            List<Free_comment> comment = commentService.comment_view(num);
            model.addAttribute("commentList",comment);
        }else if(kinds.equals("notice")){
            notice_Board post = boardService.viewBoard_notice(num);
            model.addAttribute("num",post.getNotice_num());
            model.addAttribute("writer",post.getNotice_writer());
            model.addAttribute("title",post.getNotice_title());
            model.addAttribute("content",post.getNotice_content());
            model.addAttribute("view_cnt",post.getNotice_view_count());
            model.addAttribute("comment_cnt",post.getNotice_comment_count());
            model.addAttribute("reg_date",post.getNotice_reg_date());
            model.addAttribute("from_kinds",kinds);
        }else{
            System.out.println("[viewBoard][Get]잘못된 페이지 요청");
            return "/freeBoard/view/main/1";
        }
        return"boards/PostView";
    }

    // 게시글 검색
    @GetMapping({"freeBoard/view/search","freeBoard/view/search/{page}"})
    public String searchFboard(@PathVariable(required=false) Optional<Integer> page, @RequestParam String search_option, @RequestParam String keyword, Model model){

        if(keyword.equals(""))return "redirect:/freeBoard/view/main/1"; // 키워드 없는 검색은 전체 조회(게시판 메인으로)

        if(page.isEmpty()){page= Optional.of(1);}
        List<free_Board> boardList = boardService.findBoard(search_option, keyword, page.get());
        int max = boardService.search_post_cnt(search_option,keyword).intValue();
        int[] pageInfo = pagination(page.get(), max);
        if(page.get()>pageInfo[0]){
            page = Optional.of(pageInfo[0]);
            pageInfo[1] = page.get();
        }
        if(boardList.isEmpty()){
            return "boards/NoResult";
        }
        if(page.get()>max){return "redirect:/freeBoard/view/search/"+pageInfo[2];}
        model.addAttribute("boardList",boardList);
        model.addAttribute("keyword",keyword); // 검색결과를 뷰로 전송 후 hidden에 넣었다가 다시 전송 받아서 페이지네이션 실행
        model.addAttribute("search_option",search_option); // 검색결과를 뷰로 전송 후 hidden에 넣었다가 다시 전송 받아서 페이지네이션 실행
        model.addAttribute("from","search");
        model.addAttribute("page_now",page.get());
        model.addAttribute("page_total",pageInfo[0]);
        model.addAttribute("block_start",pageInfo[1]);
        model.addAttribute("block_end",pageInfo[2]);
        return "boards/free/FreeBoardMain";
    }

    // 페이지네이션 : 메인 / 검색 용
    public int[] pagination(int page, int max){
        int start = 1;
        int total_page;
        if(page%5==0){start = (((int)page/5)-1)*5+1;}  // 마지막페이지(5배수 처리)
        else{start = ((int)page/5)*5+1;}               // 나머지
        int end = start+4;
        if(max==-1){total_page = Math.toIntExact(boardService.totalPost())+5;} // +5 : 공지글 5개로 고정이므로 5개씩 밀려서 페이징 됨 그래서 +5로 맞춰줌
        else{total_page=max;}
        if(total_page%10!=0) total_page=total_page/10+1;
        else total_page=total_page/10;
        if(total_page < end) end = total_page;          // 마지막 블록이 5개 미만이면 마지막 블록번호에 맞춰서 끝

        return new int[]{total_page, start, end};
    }
}
