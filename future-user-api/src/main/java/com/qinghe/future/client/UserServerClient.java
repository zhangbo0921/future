package com.qinghe.future.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "future-user-service")
public interface UserServerClient {
//
//    @GetMapping(value = "/user/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
//    Result<Userinfo> getUserById(@PathVariable("id") String id);
//
//    @GetMapping(value = "/user/account/{account}",produces = MediaType.APPLICATION_JSON_VALUE)
//    Result<Userinfo> loadUserByAccount(@PathVariable("account") String account);
//
//    @GetMapping(value = "/permission/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
//    Result<List<Permission>> getGrantedAuthority(@PathVariable("userId") String userId);
}
