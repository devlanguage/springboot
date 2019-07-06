package org.third.spring.boot.hello.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.third.spring.boot.hello.domain.Person;
import org.third.spring.boot.hello.domain.JsonResult;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class PersonController {

	@RequestMapping(value = "/search", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Person search(String personName) {

		return new Person(personName, 32, "hefei");

	}

	// 创建线程安全的Map
	static Map<Long, Person> users = Collections.synchronizedMap(new HashMap<Long, Person>());

	/**
	 * 根据ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getHelloPersonById(@PathVariable(value = "id") Integer id) {
		JsonResult r = new JsonResult();
		try {
			Person user = users.get(id);
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 查询用户列表
	 * 
	 * @return
	 */
	@ApiOperation(value = "获取用户列表", notes = "获取用户列表", produces = "application/json")
	@RequestMapping(value = "users", method = RequestMethod.GET, consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<JsonResult> getHelloPersonList() {
		JsonResult r = new JsonResult();
		try {
			List<Person> userList = new ArrayList<Person>(users.values());
			r.setResult(userList.toArray());
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "创建用户", notes = "根据HelloPerson对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "HelloPerson")
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> add(@RequestBody Person user) {
		JsonResult r = new JsonResult();
		try {
			users.put(user.getId(), user);
			r.setResult(user.getId());
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JsonResult> delete(@PathVariable(value = "id") Integer id) {
		JsonResult r = new JsonResult();
		try {
			users.remove(id);
			r.setResult(id);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 根据id修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "更新信息", notes = "根据url的id来指定更新用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "HelloPerson") })
	@RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Person[]> update(@PathVariable("id") Long id, @RequestBody Person user) {
		Person[] userArray = null;
		try {
			Person u = users.get(id);
			u.setName(user.getName());
			u.setAge(user.getAge());
			users.put(id, u);

			userArray = users.values().toArray(new Person[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(userArray);
	}

	@ApiIgnore // 使用该注解忽略这个API
	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	public String jsonTest() {
		return " hi you!";
	}

}
