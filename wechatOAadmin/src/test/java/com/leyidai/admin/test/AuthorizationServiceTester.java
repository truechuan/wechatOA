//package com.leyidai.admin.test;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.leyidai.admin.service.AuthorizationService;
//import com.leyidai.entity.Resource;
//import com.leyidai.entity.Role;
//
//public class AuthorizationServiceTester extends BaseTester{
//	@Autowired
//	private AuthorizationService authorizationService;
//
//	@Test
//	@Transactional
//	public void testGroup(){
//		Resource resource =  new Resource();
//		resource.setUrl("/home");
//		resource.setDescription("账户首页");
//		resource.setMenu(false);
//
//		authorizationService.addResource(resource);
//
//		Assert.assertTrue(resource.getResourceId() > 0);
//
//		Role role = new Role();
//		role.setName("后台用户查询");
//		role.setDescription("后台用户信息查询权限");
//		Set<Resource> resources = new HashSet<Resource>();
//		resources.add(resource);
//		role.setResources(resources);
//		authorizationService.addRole(role);
//
//		Resource resourceTest = new Resource();
//		resourceTest.setUrl("/test");
//		resourceTest.setDescription("测试页面");
//		resourceTest.setMenu(false);
//		authorizationService.addResource(resourceTest);
//		resources.add(resourceTest);
//
//		role.setResources(resources);
//		authorizationService.updateRole(role);
//
//		List<Resource> allResources = authorizationService.loadAllResources();
//		Assert.assertNotNull(allResources);
//		Assert.assertTrue(allResources.size() > 0);
//		for(Resource r: allResources){
//
//			Assert.assertNotNull(r.getRoles());
//			Assert.assertTrue(r.getRoles().size() > 0);
//		}
//
//	}
//
//}
