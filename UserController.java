package com.example.user.controller;

import com.example.user.domain.User;
import com.example.user.service.UserService;
import com.example.user.service.check_EmailService;
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

//@RestController
@Controller
@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "*", allowCredentials = "true")
@RequestMapping(value = "/api/projects", method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
    private final UserService userService;

    // 생성자로 연결
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @GetMapping("/users/new")
    public String joinForm(){
        return "/projects/users/createUserForm";
    }

    @PostMapping("/users/join")
    public String join(User form, HttpServletRequest request, HttpServletResponse response) throws IOException { //@RequestBody
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        //if(check_EmailService.checkAuth(form.getEmail()) == 1){
            User user = new User();
            user.setName(form.getName());
            user.setId(form.getId());
            user.setPassword(form.getPassword());
            user.setNickname(form.getNickname());
            user.setEmail(form.getEmail());
            user.setPhone(form.getPhone());
            user.setAge(form.getAge());

            String res = userService.join(user);
            if(res.equals("Join Fail")){
                out.println("<script>alert('가입에 실패했습니다')</script>");
                return "/projects/users/createUserForm";
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("loginID", user.getId());
                out.println("<script>alert('가입에 성공했습니다')</script>");
                return "/projects/users/main_login";
            }
//        }else{
//            out.println("<script>alert('이메일 인증을 완료해주세요');history.go(-1);</script>");
//            out.flush();
//            out.close();
//            return "";
//        }
//        userService.join(user);
//        return "/projects/users/login";
    }

    // 로그인 창
    @GetMapping("/users/main_login")
    public String loginForm(){
        return "/projects/users/main_login";
    }

    // 로그인
    @PostMapping("/users/login")
    public String login(@RequestParam(value="id", required=false)String id, @RequestParam(value="password", required=false)String password,
                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        Optional<User> resUser = userService.findOneUser(id);

        if(resUser.isPresent()){
            String findPassword = resUser.get().getPassword();
            if(password.equals(findPassword)){
                HttpSession session = request.getSession();
                session.setAttribute("loginID", id);
                return "/projects/users/myPage";
            }else{
                out.println("<script>alert('비밀번호를 다시 확인해 주세요');location.href='/';</script>");
                out.flush();
                out.close();
                //return "/projects/users/main_login";
                return "redirect:"+ request.getHeader("Referer");
            }
        }else{
            out.println("<script>alert('존재하지 않는 계정입니다');location.href='/';</script>");
            out.flush();
            out.close();
            return "redirect:"+ request.getHeader("Referer");
        }
    }

    // 로그아웃
    @GetMapping("/users/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("loginID");

        if(request.getHeader("Referer").contains("myPage"))
            return "redirect:/";
        System.out.println(request.getPathInfo());
        //return "redirect:"+ request.getHeader("Referer");

        return "/projects/users/logout";
    }

    // Id 중복 체크
    @PostMapping("/users/checkId/{checkid}")
    @ResponseBody
    public int checkId(@PathVariable String checkid){
        int res = userService.checkId(checkid);
        System.out.println("중복 체크 결과 (중복 : 0 / 비중복 : 1) : "+res);
        return res;
    }

    // Id 찾기
    @PostMapping("/users/find/id")
    public String findId(@RequestParam(value="name", required=false) String name, @RequestParam(value="email", required=false) String email,
                         HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String findRes = userService.findId(name, email);
        String content = name+"님의 로그인 ID는 "+findRes+"입니다.";

        if(!findRes.isEmpty()){
            System.out.println("[ID 찾기] 메일 발송 완료 : "+content);
            return "redirect:/";
        }else{
            out.println("<script>alert('이름 혹은 이메일을 다시 확인해 주세요');location.href='/';</script>");
            out.flush();
            out.close();
            return "/projects/users/find/id";
        }
    }

    // Password 찾기
    @PostMapping("/users/find/password")
    public String findPassword(@RequestParam(value="id", required=false) String id, @RequestParam(value="email", required=false) String email,
                               HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String findRes = userService.findPassword(id, email);
        String content = id+"님의 로그인 PW는 "+findRes+"입니다.";

        if(!findRes.isEmpty()){
            System.out.println("[Password 찾기] 메일 발송 완료 : "+content);
            return "redirect:/";
        }else{
            out.println("<script>alert('아이디 혹은 이메일을 다시 확인해 주세요');location.href='/';</script>");
            out.flush();
            out.close();
            return "/projects/users/find/password";
        }
    }

    // 사용자 조회
    @GetMapping("/admin/userList")
    public String list(Model model){
        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "/projects/admin/userList";
    }

    // 관리자 전용 페이지
    @GetMapping("/admin/adminMain")
    public String admin(){
        return "/projects/admin/adminMain";
    }

    // 사용자 정보 수정(관리자)
    @GetMapping("/admin/user/update")
    public String adminUpdate_form(@PathVariable String id, Model model){
        User user = userService.findOneUser(id).get();
        model.addAttribute("userInfo",user);
        return "/projects/admin/userUpdate";
    }

    @PostMapping("/admin/user/update")
    public String adminUpdate(User user){
        int res  = userService.update(user);
        if(res==1){
            System.out.println("계정정보 업데이트 성공!");
        }else{
            System.out.println("계정정보 업데이트 실패!");
        }
        return "redirect:/admin/userList";
    }

    // 사용자 삭제(관리자)
    public String adminDelete(@PathVariable String id){
        check_EmailService.deleteMail(userService.findOneUser(id).get().getEmail());
        userService.delete(id);
        return "redirect:/admin/user/userList";
    }

    // 사용자 전용 페이지
    @GetMapping("/users/myPage")
    public String page(){
        return "/projects/users/myPage";
    }

    // 사용자 정보 수정(사용자)
    public String userUpdate(){
        return "null";
    }

    // 사용자 탈퇴(사용자)
    @GetMapping("/user/withdraw")
    public String userDelete_form(Model model){
        model.addAttribute("탈퇴","withdraw");
        return "/projects/users/withdraw";
    }

    @PostMapping("/users/withdraw")
    public String userDelete(@RequestParam String password, HttpServletRequest request){
        HttpSession session = request.getSession();
        String id = session.getAttribute("loginID").toString();
        if(id.equals("Admin")){
            return "redirect:/";
        }
        User user = userService.findOneUser(id).get();
        if(user.getPassword().equals(password)){
            check_EmailService.deleteMail(user.getEmail()); // 메일 인증정보 삭제
            userService.delete(password);
            session.removeAttribute("loginID");
            return "/projects/users/withdraw";
        }else{
            System.out.println("비밀번호 불일치");
            return "redirect:/";
        }
    }
}
