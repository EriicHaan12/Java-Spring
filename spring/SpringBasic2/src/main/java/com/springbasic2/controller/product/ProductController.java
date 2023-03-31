package com.springbasic2.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbasic2.domain.ProductVO;

@Controller
public class ProductController {

	// Model 객체를 이용해서 view단으로 바인딩 하기
	@RequestMapping("viewProduct")
	// Model 파라메터값을 바인딩 하기 위한 객체
	public void viewProduct(Model model) {
		ProductVO product = new ProductVO("A001", "shrimpGGang", 2000);

		model.addAttribute("product", product);
		// viewProduct.jsp 포워딩
	}

	@RequestMapping("viewProduct2")
	// ModelAndView : view 단에 바인딩 할 객체 정보와 응답될 view단의 정보를 가지고 있는 객체
	public ModelAndView viewProduct2(Model model) {
		ProductVO product = new ProductVO("A001", "shrimpGGang", 2000);

		ModelAndView mav = new ModelAndView();

		// mav 객체에다 바인딩할 객체를 추가
		mav.addObject("product", product);

		mav.setViewName("viewProduct"); // 응답될 view 지정
		return mav;
	}

	// 페이지 redirect 하기 "redirect : (리다이렉트 할 경로)"
	@RequestMapping("viewProduct3")
	public String redirectView() {
		// redirect 할 때 view 페이지의 이름을 직접 접근 할 수 없다.
		// 반드시 controller의 매핑 주소를 이용하여 view에 접근 해야한다.

		// return "redirect:/home.jsp" -> 이렇게 치면 안됨

		// 현재 redirect: "/"<- 가 home.jsp 의 매핑 주소이므로
		// "redirect:/" 이렇게 되었을 시, 리다이렉트 할 매핑 주소가 home.jsp로 향하게 되는것

		return "redirect:/";

	}

	// 파라메터를 넘겨주면서 페이지 redirect 하기
	@RequestMapping("viewProduct4")
	public String redirectView2(RedirectAttributes rttr) {
		// hello에 넘겨줄 파라메터를 RedirectAttributes 객체에 저장
		// rttr.addAttribute("name", "dooly");
		// rttr.addAttribute("age",20);

		// addFlashAttribute() 를 사용하면 넘겨지는 파라메터가 쿼리스트링 형태로 보여지지 않는다.
		rttr.addFlashAttribute("name", "dounner");

		return "redirect:hello";

	}

	@RequestMapping("viewProduct5")

	// response를 json 타입으로 반환 해줌...
	public @ResponseBody ProductVO viewProduct3() {
		ProductVO product = new ProductVO("A001", "shrimpGGang", 2000);

		return product;
	}

	@RequestMapping(value = "savePrd")
	public String saveProductPage() {

		return "inputProduct";
	}

	// view에서 입력한 데이터를 어떻게 가져올 수 있을까
	//@RequestParam 어노테이션을 이용한다.
//	@RequestMapping(value = "savePrd", method = RequestMethod.POST)
//	public void saveProduct(@RequestParam("productNo") String prodNo, 
//							@RequestParam("productName") String prodName,
//							@RequestParam("price") int price) 
//	{
//		
//		System.out.println("save completed!");
//		ProductVO product = new ProductVO(prodNo, prodNo, price);
//		System.out.println(product.toString());
//	}
//	
	
	//만약, view에서 입력 받는 데이터들의 객체가 있다면 그 객체로 parameter의 값들을 가져올 수 있다.
	// 이때, Spring은 먼저 기본 생성자를 이용하여 productVo객체를 만든다.
	//<input 태그의 name 값을 가져와 그 name 값에 해당하는 setter를 호출하여 객체에 값을 저장>
	@RequestMapping(value = "savePrd", method = RequestMethod.POST)
	public void saveProduct(ProductVO product){
		
		System.out.println("저장 성공!");
		//ProductVO product = new ProductVO(prodNo, prodNo, price);
		System.out.println(product.toString());
	}
	
	
	
}
