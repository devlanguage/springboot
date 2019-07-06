package org.third.spring.boot.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.third.spring.boot.hello.domain.User;
import org.third.spring.boot.hello.repository.UserRepository;

@RestController
@RequestMapping("/users")

public class UserController {
	@Autowired
	private UserRepository userRepository;

	/**
	 * 从 用户存储库 获取用户列表
	 * 
	 * @return
	 */
	private List<User> getUserlist() {
		return userRepository.listUser();
	}

	/**
	 * 查询所用用户
	 * 
	 * @return
	 */
	@GetMapping
	public ModelAndView list(Model model) {
		model.addAttribute("userList", getUserlist());
		model.addAttribute("title", "用户管理");
		return new ModelAndView("users/list", "userModel", model);
	}

	/**
	 * 根据id查询用户
	 * 
	 * @return
	 */
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Long id, Model model) {
		User user = userRepository.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("title", "查看用户");
		return new ModelAndView("users/view", "userModel", model);
	}

	/**
	 * 获取 form 表单页面
	 * 
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("title", "创建用户");
		return new ModelAndView("users/form", "userModel", model);
	}

	/**
	 * 新建用户
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping
	public ModelAndView create(User user) {
		user = userRepository.saveOrUpateUser(user);
		return new ModelAndView("redirect:/users");
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id, Model model) {
		userRepository.deleteUser(id);

		model.addAttribute("userList", getUserlist());
		model.addAttribute("title", "删除用户");
		return new ModelAndView("users/list", "userModel", model);
	}

	/**
	 * 修改用户
	 */
	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		User user = userRepository.getUserById(id);

		model.addAttribute("user", user);
		model.addAttribute("title", "修改用户");
		return new ModelAndView("users/form", "userModel", model);
	}
}