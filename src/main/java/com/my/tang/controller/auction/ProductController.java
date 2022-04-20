package com.my.tang.controller.auction;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.my.tang.controller.list.ItemViewService;
import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.PageHandler;
import com.my.tang.domain.etc.SearchCondition;
import com.my.tang.domain.member.User;
import com.my.tang.service.auction.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
        if (!new File(uploadPath).exists()) {
            new File(uploadPath).mkdirs();
        }

        //파일 복사
        try {
            FileCopyUtils.copy(file.getBytes(), target);
            m.addAttribute("file", file);
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("file", "error");
        }

        String upload = uploadPath + fileName;
        productDto.setP_img1(upload);


//        try {
//            // p_rdate
////            productDto.setP_rdate(remain2(productDto));
//            remain2(productDto);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        System.out.println( request.getParameter("p_num").getClass().getName());
//        if (request.getParameter("p_num") != null || !request.getParameter("p_num").equals("")) {
//            System.out.println(request.getParameter("p_num"));
//        }

//        Integer test = null;
//        if (request.getParameter("p_num") != null || !request.getParameter("p_num").equals("")) {
//            test = Integer.parseInt(request.getParameter("p_num"));
//        }



        //System.out.println(productDto);
        //System.out.println(p_num);
        String writer = (String) session.getAttribute("id");
        productDto.setM_id(writer);
        //System.out.println(writer);
        productDto.setCustomer_id(writer);
        //System.out.println(productDto.getCustomer_id());
//        if (productDto.getFlag_1() == null || productDto.getFlag_1().equals("")) {
//            productDto.setFlag_1(productDto.getCustomer_id());
//        }


//        if (productDto.getFlag_1() == null || productDto.getFlag_1().equals("")) {
//            productDto.setFlag_1(productDto.getCustomer_id());
//        } else if  (productDto.getFlag_2() == null || productDto.getFlag_2().equals("")) {
//            productDto.setFlag_2(productDto.getCustomer_id());
//        } else if  (productDto.getFlag_3() == null || productDto.getFlag_3().equals("")) {
//            productDto.setFlag_3(productDto.getCustomer_id());
//        } else if  (productDto.getFlag_4() == null || productDto.getFlag_4().equals("")) {
//            productDto.setFlag_4(productDto.getCustomer_id());
//        } else if  (productDto.getFlag_5() == null || productDto.getFlag_5().equals("")) {
//            productDto.setFlag_5(productDto.getCustomer_id());
//        }


//        if(productDto.getFlag_1() != null && !productDto.getFlag_1().equals("") && productDto.getFlag_2() != null && !productDto.getFlag_2().equals("") &&
//                productDto.getFlag_3() != null && !productDto.getFlag_3().equals("") && productDto.getFlag_4() != null && !productDto.getFlag_4().equals("") &&
//                productDto.getFlag_5() != null && !productDto.getFlag_5().equals("")) {
//            Set set = new HashSet();
//            set.add(productDto.getFlag_1());
//            set.add(productDto.getFlag_2());
//            set.add(productDto.getFlag_3());
//            set.add(productDto.getFlag_4());
//            set.add(productDto.getFlag_5());
//
//            Iterator<String> iter = set.iterator(); // set을 Iterator 안에 담기
//            while(iter.hasNext()) { // iterator에 다음 값이 있다면
//                if (productDto.getFlag_1() != null && !productDto.getFlag_1().equals("")){
//                    productDto.setFlag_1(iter.next()); // iter에서 값 꺼내서 집어 넣기
//                } else if (productDto.getFlag_2() != null && !productDto.getFlag_2().equals("")) {
//                    productDto.setFlag_2(iter.next());
//                } else if (productDto.getFlag_3() != null && !productDto.getFlag_3().equals("")) {
//                    productDto.setFlag_3(iter.next());
//                } else if (productDto.getFlag_4() != null && !productDto.getFlag_4().equals("")) {
//                    productDto.setFlag_4(iter.next());
//                } else if (productDto.getFlag_5() != null && !productDto.getFlag_5().equals("")) {
//                    productDto.setFlag_5(iter.next());
//                }
//            }
//        }




        //System.out.println(productDto.getFlag_1());

        try {
            if (productService.write(productDto) != 1) //write 메소드가 각각의 request.parameter 역할을 한다?
                throw new Exception("Write failed.");

//            Map map = new HashMap();
//            List<ProductDto> list = productService.getPage(map);
            //System.out.println(productDto.getFlag_1());
            rattr.addFlashAttribute("msg", "WRT_OK");
            //System.out.println(productDto.getP_title()); //productDto는 다 보낼 수 있음.. 그 각각의 값이 문제
            m.addAttribute("po", productDto);      // 등록하려던 내용을 보여줘야 함.





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
            //System.out.println(p_num);
            ProductDto productDto = productService.read(p_num);

            Integer no = productDto.getP_num();
            ItemViewService itemViewService = new ItemViewService();
            ProductDto article = itemViewService.getArticle(no);


            m.addAttribute("article", article);
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
    public String list(SearchCondition sc,  Integer page, Integer pageSize, Model m, HttpSession session) {


        if(page==null) page=1;
        if(pageSize==null) pageSize=10;

        try {

//            Map map = new HashMap();
//            map.put("offset", (page-1)*pageSize);
//            map.put("pageSize", pageSize);

//            int totalCnt = productService.getCount();
            int totalCnt = productService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);
//            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<ProductDto> list = productService.getSearchResultPage(sc);
//            List<ProductDto> list2 = productService.getPage(map);

            //System.out.println(list.get(0).getP_num());






//            ProductDto productDto = productService.read(p_num);
//
//
//            Integer no = productDto.getP_num();
//            ItemViewService itemViewService = new ItemViewService();
//            ProductDto article = itemViewService.getArticle(no);
            //List<Integer> list2 = productService.selectPnum();

            //System.out.println(productDto);
            //System.out.println(p_num);

            //String p_date = productService.selectPdate(p_num);

//            m.addAttribute("dto", dto);
            //m.addAttribute("p_date", p_date);
            //m.addAttribute("list2", list2);
//            m.addAttribute("article", article);
//            m.addAttribute("dto", productDto);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());

//            Integer p_num = (Integer) session.getAttribute("p_num");
//            ProductDto productDto = productService.readList(p_num);


            //ProductDto dto = productService.read(p_num);

//            Integer p_num = Integer.parseInt(request.getParameter("p_num"));
//            System.out.println(productService.selectPnum(p_num));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/auction/list";
    }


    @RequestMapping(value = "/list2", method= {RequestMethod.GET, RequestMethod.POST})
    public String list2(SearchCondition sc,  Integer page, Integer pageSize, Model m, HttpSession session) {


        if(page==null) page=1;
        if(pageSize==null) pageSize=10;

        try {
            int totalCnt = productService.getSearchResultCntList(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<ProductDto> list = productService.getSearchResultPageList(sc);

            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "auction/list";
    }


//    @GetMapping("/modify")
//    public String modify(HttpServletRequest request) {
//
//        return "auction/product"; // 읽기와 쓰기에 사용, 쓰기에 사용할때는 mode=new
//    }


    @RequestMapping(value = "/modify", method= {RequestMethod.GET, RequestMethod.POST})
    public String modify(User user, ProductDto productDto, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        try {
            if (productService.modify(productDto)!= 1) // 메소드가 실행이 되어야 DB값이 변함
                throw new Exception("Modify failed.");


            // DB에서 한 줄 읽어 오는 소스
            Integer no = productDto.getP_num();
            //System.out.println(productDto.getP_num());
            ItemViewService itemViewService = new ItemViewService();
            ProductDto article = itemViewService.getArticle(no);


//            if (article.getA_price() == article.getP_eprice()) {
//                article.setClassify("즉시구매 하셨습니다");
//            }

//            Calendar now= Calendar.getInstance();
//
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            Date date = format.parse(article.getP_date()); // String -> Date
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(date);  // Date -> Calendar
//
//            long diffSec = (now.getTimeInMillis() - cal.getTimeInMillis()) / 1000;
//
//            if (article.getA_price() < article.getP_eprice()) {
//                if (diffSec >= 0 ) {
//                    article.setClassify("경매종료(시간초과)");
//                } else {
//                    article.setClassify("진행중");
//                }
//            }

            String customer_id = (String)session.getAttribute("id"); //수정할 때 접속 id를 customer_id에 입력
            article.setCustomer_id(customer_id);

            article.setA_price(article.getA_price() + article.getP_plus()); //현재가는 현재값 + 추가입찰금액
            article.setA_count(article.getA_count() + 1); //입찰수는 하나 더해지고

            int a_price = article.getA_price();
            int p_eprice = article.getP_eprice();
            if(a_price > p_eprice) {   // 현재가가 즉구가보다 높으면
                //a_price = p_eprice;
                article.setA_price(article.getP_eprice()); // 현재가에 즉구가 입력
            }

            if(article.getCustomer_id().equals(customer_id)) { //손님 id값이 같으면
                if(article.getFlag_1().equals("") || article.getFlag_1() == null) { //flag가 널값이면
                    article.setFlag_1(article.getCustomer_id()); //거기에 손님 아이디값 저장해 두기
                } else if(!article.getFlag_1().equals(article.getCustomer_id()) && (article.getFlag_2().equals("") ||
                        article.getFlag_2() == null)) { //flag가 널값이면서 중복된 아이디는 입력 안 하기
                    article.setFlag_2(article.getCustomer_id());
                } else if(!article.getFlag_1().equals(article.getCustomer_id()) && !article.getFlag_2().equals(article.getCustomer_id()) &&
                        (article.getFlag_3().equals("") || article.getFlag_3() == null)) {
                    article.setFlag_3(article.getCustomer_id());
                } else if(!article.getFlag_1().equals(article.getCustomer_id()) && !article.getFlag_2().equals(article.getCustomer_id()) &&
                        !article.getFlag_3().equals(article.getCustomer_id()) && (article.getFlag_4().equals("") || article.getFlag_4() == null)) {
                    article.setFlag_4(article.getCustomer_id());
                } else if(!article.getFlag_1().equals(article.getCustomer_id()) && !article.getFlag_2().equals(article.getCustomer_id()) &&
                        !article.getFlag_3().equals(article.getCustomer_id()) && !article.getFlag_4().equals(article.getCustomer_id()) &&
                        (article.getFlag_5().equals("") || article.getFlag_5() == null)) {
                    article.setFlag_5(article.getCustomer_id());
                }
            }

//            if(article.getCustomer_id().equals(customer_id)) { //손님 id값이 같으면
//                article.setP_status(article.getP_status() + 1); // 상태값을 1 올리고 저장
//                if(article.getFlag_1().equals("") || article.getFlag_1() == null) { //flag가 널값이면
//                    article.setFlag_1(article.getA_nprice() + "/" + article.getCustomer_id() + "/" + article.getP_status()); //거기에 손님 정보값 저장해 두기
//                } else if(!article.getFlag_1().contains(article.getCustomer_id()) && (article.getFlag_2().equals("") ||
//                        article.getFlag_2() == null)) { //flag가 널값이면서 아이디값이 포함된 값은 입력 안 하기
//                    article.setFlag_2(article.getA_nprice() + "/" + article.getCustomer_id() + "/" + article.getP_status());
//                } else if(!article.getFlag_1().contains(article.getCustomer_id()) && !article.getFlag_2().contains(article.getCustomer_id()) &&
//                        (article.getFlag_3().equals("") || article.getFlag_3() == null)) {
//                    article.setFlag_3(article.getA_nprice() + "/" + article.getCustomer_id() + "/" + article.getP_status());
//                } else if(!article.getFlag_1().contains(article.getCustomer_id()) && !article.getFlag_2().contains(article.getCustomer_id()) &&
//                        !article.getFlag_3().contains(article.getCustomer_id()) && (article.getFlag_4().equals("") || article.getFlag_4() == null)) {
//                    article.setFlag_4(article.getA_nprice() + "/" + article.getCustomer_id() + "/" + article.getP_status());
//                } else if(!article.getFlag_1().contains(article.getCustomer_id()) && !article.getFlag_2().contains(article.getCustomer_id()) &&
//                        !article.getFlag_3().contains(article.getCustomer_id()) && !article.getFlag_4().contains(article.getCustomer_id()) &&
//                        (article.getFlag_5().equals("") || article.getFlag_5() == null)) {
//                    article.setFlag_5(article.getA_nprice() + "/" + article.getCustomer_id() + "/" + article.getP_status());
//                }
//            }




            if(article.getA_count() > 0) { // 상태값이 0보다 크면
                article.setA_nprice(article.getA_price()); //현재값 입찰값에 저장
            }



//            if(article.getP_status() > 0 && (article.getA_nprice() == article.getA_price())) {
//                article.setClassify("진행중 - 현재 최고가 입찰");
//           }

//            String flag_1 = article.getFlag_1();
//            String flag_2 = article.getFlag_2();
//            String flag_3 = article.getFlag_3();
//            String flag_4 = article.getFlag_4();
//            String flag_5 = article.getFlag_5();
//            Boolean bid_checked = article.isBid_checked();
//
//            if (customer_id.equals(flag_1)) {
//                article.setBid_checked(true);
//            } else if (customer_id.equals(flag_2)) {
//                article.setBid_checked(true);
//            } else if (customer_id.equals(flag_3)) {
//                article.setBid_checked(true);
//            } else if (customer_id.equals(flag_4)) {
//                article.setBid_checked(true);
//            } else if (customer_id.equals(flag_5)) {
//                article.setBid_checked(true);
//            }
            //포인트 처리
            String id = (String)session.getAttribute("id");
            article.setM_point(productService.selectUser(id).getM_point());
            //productService.updateFlag(article.isP_plus_flag(), article.getP_num());
            //System.out.println("1");

//            if (!article.getFlag_1().equals(article.getCustomer_id()) && !article.getFlag_2().equals(article.getCustomer_id()) &&
//                    !article.getFlag_3().equals(article.getCustomer_id()) && !article.getFlag_4().equals(article.getCustomer_id()) &&
//                    !article.getFlag_5().equals(article.getCustomer_id())) { //입찰한 경험이 없으면 입찰가 빼주고, 입찰한 경험이 있으면 p_plus만큼 빼주기
//                article.setM_point(article.getM_point() - article.getA_nprice());
//            }  else {
//                article.setM_point(article.getM_point() - article.getP_plus());
//            }


            if (article.isP_plus_flag() == false){ //처음에는 현재 포인트에서 입찰가 빼주기, 두 번째부터는 p_plus만큼 빼주기
                article.setM_point(article.getM_point() - article.getA_nprice());
                article.setP_plus_flag(true);
                //System.out.println("2");
            } else {
                article.setM_point(article.getM_point() - article.getP_plus());
                //System.out.println("3" + article.getP_plus());
            }



//            article.setClassify("입찰");

            productService.updatePoint(article.getM_point(), id);

//            //System.out.println("0");
//            //즉시구매
//            if (article.getA_price() == article.getP_eprice()) {
//                //System.out.println("1");
//                if (customer_id.equals(id)) {
//                    //System.out.println("2");
//                    article.setClassify("즉시구매");
//                    article.setIn_point(-(article.getP_eprice()));
//                }
//            }
//
//            //낙찰
//            Calendar now= Calendar.getInstance();
//
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            Date date = format.parse(article.getP_date()); // String -> Date
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(date);  // Date -> Calendar
//
//            long diffSec = (now.getTimeInMillis() - cal.getTimeInMillis()) / 1000;
//
//            //System.out.println("지금" + now.getTimeInMillis());
//            //System.out.println("마감" + cal.getTimeInMillis());
//
//            if (article.getP_eprice() != article.getA_price() && diffSec >= 0) {
//                if (customer_id.equals(id)) {
//                    article.setClassify("낙찰");
//                    article.setIn_point(-(article.getA_price()));
//                }
//            }
//
//            //System.out.println("0");
//            //판매 수익
//            if(diffSec >= 0 || (article.getA_price() == article.getP_eprice())) {
//                //System.out.println("1");
//                if (article.getM_id().equals(id)) {
//                    //System.out.println("2");
//                    article.setClassify("판매수익");
//                    article.setIn_point(article.getA_price());
//                }
//            }


           //System.out.println("중요" + article.getA_price() + article.getP_plus());
            productService.modify(article);

            Calendar now= Calendar.getInstance();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = format.parse(article.getP_date()); // String -> Date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);  // Date -> Calendar

            long diffSec = (now.getTimeInMillis() - cal.getTimeInMillis()) / 1000;

            //System.out.println("지금" + now.getTimeInMillis());
            //System.out.println("마감" + cal.getTimeInMillis());

            //System.out.println("0");
            //판매 수익
            if(diffSec >= 0 || (article.getA_price() == article.getP_eprice())) {
                //System.out.println("1");
                if (article.getM_id().equals(article.getM_id())) {
                    //System.out.println("2");
                    article.setClassify_sell(article.getM_id()+"/판매수익");
                    article.setIn_point_sell(article.getA_price());
                }
            }


            //System.out.println("0");
            //즉시구매
            if (article.getA_price() == article.getP_eprice()) {
                //System.out.println("1");
                if (article.getCustomer_id().equals(id)) {
                    //System.out.println("2");
                    article.setClassify_buy(article.getCustomer_id()+"/낙찰");
                    article.setIn_point_buy(-(article.getP_eprice()));
                }
            }

            //낙찰
            if (article.getP_eprice() != article.getA_price() && diffSec >= 0) {
                if (article.getCustomer_id().equals(id)) {
                    article.setClassify_buy(article.getCustomer_id()+"/낙찰");
                    article.setIn_point_buy(-(article.getA_price()));
                }
            }


            productService.updateClassify(article);



            //System.out.println(article.getP_plus());

            m.addAttribute("article", article);

            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/product/list"+sc.getQueryString();
        } catch (Exception e) {
            e.printStackTrace();

//            Integer no = productDto.getP_num();
//            ItemViewService itemViewService = new ItemViewService();
//            ProductDto article = itemViewService.getArticle(no);
//
//            m.addAttribute("article", article);
            m.addAttribute(productDto);
            m.addAttribute("msg", "MOD_ERR");
            return "/auction/productList";
        }
    }

//    @RequestMapping("sort")
//    public String sort(ModelAndView view, SearchCondition sc, Model m, Integer page, Integer pageSize,
//                             @RequestParam("order_by")String order_by) {
//
//        if(page==null) page=1;
//        if(pageSize==null) pageSize=10;
//
//        List<ProductDto> list = null;
//        try {
//            list = productService.getSearchResultPage(sc);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //정렬기준 처리
//        ProductComparator com = new ProductComparator();
//        com.order_by = order_by;
//        Collections.sort(list,com);
//
//        Map map = new HashMap();
//        map.put("list", list);
//        map.put("com", com);
//        map.put("offset", (page-1)*pageSize);
//        map.put("pageSize", pageSize);
//
//        try {
//            int totalCnt = productService.getSearchResultCnt(sc);
//            m.addAttribute("totalCnt", totalCnt);
//            PageHandler pageHandler = new PageHandler(totalCnt, sc);
//            List<ProductDto> result = productService.getPage(map);
//
//            view.addObject("data",result);
//            view.setViewName("productList");
//
//            m.addAttribute("list", list);
//            m.addAttribute("ph", pageHandler);
//            m.addAttribute("page", page);
//            m.addAttribute("pageSize", pageSize);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "auction/list";
//    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }

//     static String remain(ProductDto productDto) throws ParseException {
//         Date format1 = productDto.getP_date();
//         Date format2 = new Date();
//         System.out.println("format1 = " + format1);
//         System.out.println("format2 = " + format2);
//
//         long diffSec = (format1.getTime() - format2.getTime()) / 1000; //초 차이
//         long diffMin = (format1.getTime() - format2.getTime()) / 60000; //분 차이
//         long diffHor = (format1.getTime() - format2.getTime()) / 3600000; //시 차이
//         long diffDays = diffSec / (24 * 60 * 60); //일자수 차이
//
//         if (diffSec<60)
//         return String.format("%d초 남음", diffSec);
//         else if (diffMin < 60)
//         return String.format("%d분 남음", diffMin);
//         else if (diffHor < 24)
//         return String.format("%d시간 남음", diffHor);
//         else
//         return String.format("%d일 남음", diffDays);
//     }
//
//    static void remain2(ProductDto productDto) throws ParseException {
//         java.util.Calendar cal    = java.util.Calendar.getInstance(new Locale("Korean", "Korea"));
//         java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//         double remain_day   = 0;
//         long remain_time    = 0;
//            int day = 0;
//            int hh  = 0;
//            int mm  = 0;
//            int ss  = 0;
//
//         // 현재시간
//         long l_currtime         = cal.getTime().getTime();
//
//         productDto.setP_sdate(new Date());
////         System.out.println(productDto.getP_sdate());
//
//         // 시작시간
//         java.util.Date s_time   = productDto.getP_sdate();
//         cal.setTime(s_time);
//         java.sql.Date ss_date   = new java.sql.Date(cal.getTime().getTime());
//            long l_s_time           = ss_date.getTime();
//
//         // 종료시간
//         java.util.Date e_time   = productDto.getP_date();
//         cal.setTime(e_time);
//         java.sql.Date ee_date   = new java.sql.Date(cal.getTime().getTime());
//            long l_e_time           = ee_date.getTime();
//
//         // 남은시간
//         double d_remain_time = (double)(l_e_time - l_currtime) /24/60/60/1000 ;
//
//         if ( l_currtime > l_s_time && d_remain_time > 0 )
//            {
//                day = (int)(d_remain_time);  // 일자
//                remain_day = ( d_remain_time - (double)day ) * 24;
//
//                hh  = (int)(remain_day);     // 시간
//                remain_day = ( remain_day - (double)hh ) * 60;
//
//                mm  = (int)(remain_day);     // 분
//                remain_day = ( remain_day - (double)mm ) * 60;
//
//                ss  = (int)(remain_day);     // 초
//            }
//        System.out.println("day : " + day);
//        System.out.println("hh : " + hh);
//        System.out.println("mm : " + mm);
//        System.out.println("ss : " + ss);
//
//
//
//    }
}
