package com.my.tang.controller.auction;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.PageHandler;
import com.my.tang.domain.etc.SearchCondition;
import com.my.tang.service.auction.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    //properties에 있는 uploadPath값 가져오기
    @Resource(name="uploadPath")
    String uploadPath;

    @GetMapping("/write")
    public String write(HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동
        return "auction/product"; // 읽기와 쓰기에 사용, 쓰기에 사용할때는 mode=new
    }

    @PostMapping("/write")
    public String write(MultipartFile file, ProductDto productDto, RedirectAttributes rattr, Model m, HttpServletRequest request, HttpSession session) {
        String fileName = file.getOriginalFilename();

        File target = new File(uploadPath, fileName);

        //경로 생성
        if ( ! new File(uploadPath).exists()) {
            new File(uploadPath).mkdirs();
        }

        //파일 복사
        try {
            FileCopyUtils.copy(file.getBytes(), target);
            m.addAttribute("file", file);
        } catch(Exception e) {
            e.printStackTrace();
            m.addAttribute("file", "error");
        }

        String upload = uploadPath+fileName;
        productDto.setP_img1(upload);

        try {
              remain(productDto);
//            productDto.setP_rdate(String.valueOf(remainTime));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String writer = (String)session.getAttribute("id");
        productDto.setM_id(writer);

        try {
            if (productService.write(productDto) != 1)
                throw new Exception("Write failed.");

            rattr.addFlashAttribute("msg", "WRT_OK");

            return "redirect:/product/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("po", productDto);      // 등록하려던 내용을 보여줘야 함.
            return "auction/product";
        }
    }

    @GetMapping("/read")
    public String read(Integer p_num, Integer page, Integer pageSize, Model m) {
        try {
            ProductDto productDto = productService.read(p_num);
            m.addAttribute("productDto", productDto); //아래 문장과 동일
//            m.addAttribute(boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "auction/productList";
    }


    @RequestMapping(value = "/list", method= {RequestMethod.GET, RequestMethod.POST})
    public String list(ProductDto productDto, Integer page, Integer pageSize, Model m, HttpSession session) {


        if(page==null) page=1;
        if(pageSize==null) pageSize=10;

        try {

            int totalCnt = productService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

            Map map = new HashMap();
            map.put("offset", (page-1)*pageSize);
            map.put("pageSize", pageSize);

            List<ProductDto> list = productService.getPage(map);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "auction/list";
    }

    @PostMapping("/modify")
    public String modify(ProductDto productDto, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        String m_id = (String)session.getAttribute("id");
        productDto.setM_id(m_id);

        try {
            if (productService.modify(productDto)!= 1)
                throw new Exception("Modify failed.");

            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/product/list"+sc.getQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(productDto);
            m.addAttribute("msg", "MOD_ERR");
            return "auction/product";
        }
    }

    @PostMapping("/remove")
    public String remove(Integer p_num, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        String m_id = (String)session.getAttribute("id");

        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            int rowCnt = productService.remove(p_num, m_id);

            if(rowCnt!=1)
                throw new Exception("board remove error");

            rattr.addFlashAttribute("msg","DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");
        }

        return "redirect:/auction/list"; //모델에 담으면 redirect 시 값이 자동으로 뒤에 붙음
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }

     static String remain(ProductDto productDto) throws ParseException {
         String date1 = String.valueOf(productDto.getP_date()); //날짜1
         String date2 = String.valueOf(productDto.getP_sdate()); //날짜2

         Date format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date1);
         Date format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date2);

         long diffSec = (format1.getTime() - format2.getTime()) / 1000; //초 차이
         long diffMin = (format1.getTime() - format2.getTime()) / 60000; //분 차이
         long diffHor = (format1.getTime() - format2.getTime()) / 3600000; //시 차이
         long diffDays = diffSec / (24 * 60 * 60); //일자수 차이

         if (diffSec<60)
         return String.valueOf(diffSec + "초 남음");
         else if (diffMin < 60)
         return String.valueOf(diffMin + "분 남음");
         else if (diffHor < 24)
         return String.valueOf(diffHor + "시간 남음");
         else
         return String.valueOf(diffDays + "일 남음");
     }

//    static void remain2(ProductDto productDto) throws ParseException {
//        // TODO Auto-generated method stub
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.M", Locale.KOREA);
//        Date d1 = f.parse(String.valueOf(productDto.getP_date()));
//        Date d2 = f.parse(String.valueOf(productDto.getP_sdate()));
//        long diff = d1.getTime() - d2.getTime();
//        long sec = diff / 1000;
//        System.out.println(sec);
//    }


}
