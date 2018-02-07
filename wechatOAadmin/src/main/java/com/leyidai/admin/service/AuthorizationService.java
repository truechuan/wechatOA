package com.leyidai.admin.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.admin.mapper.GroupMapper;
import com.leyidai.admin.mapper.ResourceCategoryMapper;
import com.leyidai.admin.mapper.ResourceMapper;
import com.leyidai.admin.mapper.RoleMapper;
import com.leyidai.admin.util.DateUtil;
import com.leyidai.admin.util.DateUtil.DateFormat;
import com.leyidai.entity.Group;
import com.leyidai.entity.Resource;
import com.leyidai.entity.ResourceCategory;
import com.leyidai.entity.Role;

@Service
public class AuthorizationService {
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private ResourceCategoryMapper rcategoryMapper;
	@Autowired
	private DateUtil dateUtil;
	
	@Transactional
	public int addGroup(Group group){
		
		group.setCreateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		groupMapper.addGroup(group);
		
		if(group.getRoles() != null){
			
			for(Role role: group.getRoles()){
				
				groupMapper.addGroupRole(group.getGroupId(), role.getRoleId());
			}
		}
		return group.getGroupId();
	}
	
	@Transactional
	public void updateGroup(Group group){
		
		group.setUpdateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		groupMapper.updateGroup(group);
		groupMapper.deleteGroupRole(group.getGroupId());
		
		if(group.getRoles() != null){
			
			for(Role r: group.getRoles()){
				groupMapper.addGroupRole(group.getGroupId(), r.getRoleId());
			}
		}
	}
	
	@Transactional(readOnly=true)
	public Group getGroupById(int groupId){
		
		Group group = groupMapper.getGroup(groupId);
		if(group != null){
			group.setRoles(roleMapper.getGroupRoles(group.getGroupId()));
		}
		return group;
	}
	
	@Transactional
	public int addRole(Role role){
		
		role.setCreateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		roleMapper.addRole(role);
		
		if(role.getResources() != null){
			
			for(Resource r: role.getResources()){
				roleMapper.addRoleResource(role.getRoleId(), r.getResourceId());
			}
		}
		return role.getRoleId();
	}
	
	@Transactional
	public void updateRole(Role role){
		
		role.setCreateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		roleMapper.updateRole(role);
		
		if(role.getResources() != null){
			
			roleMapper.deleteRoleResource(role.getRoleId());
			
			for(Resource r: role.getResources()){
				roleMapper.addRoleResource(role.getRoleId(), r.getResourceId());
			}
		}
	}
	
	@Transactional(readOnly=true)
	public Role getRoleById(int roleId){
		
		Role role = roleMapper.getRole(roleId);
		if(role != null){
			role.setResources(resourceMapper.getRoleResources(role.getRoleId()));
		}
		return role;
	}
	
	@Transactional
	public int addResource(Resource resource){
		
		resourceMapper.addResource(resource);
		return resource.getResourceId();
	}
	
	@Transactional
	public void updateResource(Resource resource){
		
		resourceMapper.updateResource(resource);
	}
	
	@Transactional(readOnly=true)
	public Resource getResourceById(int resourceId){
		
		return resourceMapper.getResource(resourceId);
	}
	
	@Transactional(readOnly=true)
	public List<Resource> loadAllResources(){
		
		List<Resource> resources = resourceMapper.getAllResources();
		if(resources != null){
			for(Resource r: resources){
				r.setRoles(roleMapper.getResourceRoles(r.getResourceId()));
			}
		}
		return resources;
	}
	
	@Transactional(readOnly=true)
	public Set<Group> getUserGroups(int adminUserId){
		
		List<Group> userGroups = groupMapper.getUserGroups(adminUserId);
		if(userGroups != null){
			for(Group g: userGroups){
				g.setRoles(roleMapper.getGroupRoles(g.getGroupId()));
			}
		}
		return new HashSet<Group>(userGroups);
	}
	
	@Transactional(readOnly=true)
	public Set<Resource> getRoleResources(int roleId){
		
		return resourceMapper.getRoleResources(roleId);
	}
	
	@Transactional(readOnly=true)
	public boolean isRoleExist(String name){
		
		return roleMapper.getRoleByName(name)!=null;
	}
	
	@Transactional(readOnly=true)
	public boolean isGroupExist(String name){
		
		return groupMapper.getGroupByName(name)!=null;
	}
	
	@Transactional(readOnly=true)
	public boolean isResourceExist(String url){
		
		return resourceMapper.getResourceByUrl(url)!=null;
	}
	@Transactional(readOnly=true)
	public int totalRoleCount(){
		
		return roleMapper.getRoleCount();
	}
	
	@Transactional(readOnly=true)
	public List<Role> getRoles(int start, int size){
		
		List<Role> roles = roleMapper.getRoles(start, size);
		
		if(roles != null){
			for(Role r: roles){
				r.setResources(resourceMapper.getRoleResources(r.getRoleId()));
			}
		}
		return roles;
	}
	@Transactional
	public void deleteRole(int roleId){
		
		roleMapper.deleteRole(roleId);
	}
	@Transactional
	public void deleteResource(int resourceId){
		
		resourceMapper.deleteResource(resourceId);
	}
	
	@Transactional(readOnly=true)
	public List<Group> getGrups(){
		
		return groupMapper.getAllGroups();
	}
	
	@Transactional(readOnly=true)
	public int getGrupsByAdminUserId(int adminUserId){
		
		return groupMapper.getGrupsByAdminUserId(adminUserId);
	}
	
	@Transactional(readOnly=true)
	public int totalResourceCount(){
		
		return resourceMapper.getResourceCount();
	}
	
	@Transactional(readOnly=true)
	public List<Resource> getResources(int start, int size){
		
		return resourceMapper.getResources(start, size);
	}
	
	@Transactional(readOnly=true)
	public List<Role> getRoles(){
		return roleMapper.getAllRoles();
	}
	
	@Transactional(readOnly=true)
	public int totalGroupCount(){
		
		return groupMapper.getGroupCount();
	}
	
	@Transactional(readOnly=true)
	public List<Group> getGroups(int start, int size){
		
		List<Group> userGroups = groupMapper.getGroups(start, size);
		if(userGroups != null){
			for(Group g: userGroups){
				g.setRoles(roleMapper.getGroupRoles(g.getGroupId()));
			}
		}
		return userGroups;
	}
	
	@Transactional
	public void deleteGroup(int groupId){
		
		groupMapper.deleteGroup(groupId);
	}
	@Transactional(readOnly=true)
	public boolean isResourceCategoryExist(String name){
		
		return rcategoryMapper.getResourceCategoryByName(name)!=null;
	}
	@Transactional
	public void addResourceCategory(ResourceCategory rcategory){
		
		rcategoryMapper.addResourceCategory(rcategory);
		if(rcategory.getResources() != null){
			for(Resource r: rcategory.getResources()){
				rcategoryMapper.addRelatedResourceCategory(rcategory.getRcategoryId(), r.getResourceId());
			}
		}
	}
	@Transactional
	public void updateResourceCategory(ResourceCategory rcategory){
		
		rcategoryMapper.deleteRelatedResourceCategry(rcategory.getRcategoryId());
		
		rcategoryMapper.updateResourceCategory(rcategory);
		if(rcategory.getResources() != null){
			for(Resource r: rcategory.getResources()){
				rcategoryMapper.addRelatedResourceCategory(rcategory.getRcategoryId(), r.getResourceId());
			}
		}
	}
	@Transactional(readOnly=true)
	public ResourceCategory getResourceCategory(int rcategoryId){
		
		ResourceCategory rcategory = rcategoryMapper.getResourceCategoryById(rcategoryId);
		if(rcategory != null){
			rcategory.setResources(resourceMapper.getResourcesByResourceCategoryId(rcategoryId));
		}
		
		return rcategory;
	}
	
	@Transactional(readOnly=true)
	public int totalResourceCatgeoryCount(){
		
		return rcategoryMapper.getResourceCategoryCount();
	}
	
	@Transactional(readOnly=true)
	public List<ResourceCategory> getResourceCategorys(int start, int size){
		
		List<ResourceCategory> rcategories = rcategoryMapper.getResourceCategorys(start, size);
		if(rcategories != null){
			for(ResourceCategory rc: rcategories){
				rc.setResources(resourceMapper.getResourcesByResourceCategoryId(rc.getRcategoryId()));
			}
		}
		return rcategories;
	}
	@Transactional
	public void deleteResourceCategory(int rcategoryId){
		
		rcategoryMapper.deleteResourceCategory(rcategoryId);
	}
	@Transactional(readOnly=true)
	public List<ResourceCategory> getResourceCategoryByResourceId(int resourceId){
		
		return rcategoryMapper.getResourceCategoryByResourceId(resourceId);
	}
}
