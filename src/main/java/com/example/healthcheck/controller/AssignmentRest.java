package com.example.healthcheck.controller;

import com.example.healthcheck.entity.Account;
import com.example.healthcheck.entity.Assignment;
import com.example.healthcheck.entity.JoinKeys;
import com.example.healthcheck.service.AccountService;
import com.example.healthcheck.service.AssignmentService;
import com.example.healthcheck.service.JoinKeysService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
public class AssignmentRest {
    private AssignmentService assignmentService;
    private AccountService accountService;
    private JoinKeysService joinKeysService;

    @Autowired
    public AssignmentRest(AssignmentService assignmentService,AccountService accountService,JoinKeysService joinKeysService){
        this.assignmentService=assignmentService;
        this.accountService = accountService;
        this.joinKeysService=joinKeysService;
    }

    @RequestMapping(value = "/v1/assignments",method = RequestMethod.GET)
    public ResponseEntity<List<Assignment>> getAll(HttpServletRequest request){
        String value = request.getQueryString();
        if (request.getContentLength() < 0 && value == null) {
            List<Assignment> list = assignmentService.findAll();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .cacheControl(CacheControl.noCache())
                    .body(list);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .cacheControl(CacheControl.noCache())
                    .build();
        }

    }

    @RequestMapping(value = "/v1/assignments",method = RequestMethod.POST)
    public ResponseEntity<Assignment> addContent(@RequestBody Assignment assignment,@RequestHeader("Authorization") String value){
        String[] str= value.split(" ");
        byte[] bytes = Base64.getDecoder().decode(str[1].getBytes());
        String st=new String(bytes);
        String[] s =st.split(":");

        List<Account> account = accountService.findByEmail(s[0]);
        Assignment dbAssignment = null;

        if(account.size()>0){
            try{
                dbAssignment = assignmentService.save(assignment);
                joinKeysService.save(new JoinKeys(s[0], dbAssignment.getId()));
            }catch (Exception e) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .cacheControl(CacheControl.noCache())
                        .build();
            }
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .cacheControl(CacheControl.noCache())
                .body(dbAssignment);
    }

    @RequestMapping(value = "/v1/assignments/{id}",method = RequestMethod.GET)
    public ResponseEntity<Assignment> getById(@PathVariable String id,HttpServletRequest request){
        Assignment assignment=null;
        if (request.getContentLength() < 0){
            assignment=assignmentService.findById(id);
            if(assignment==null)
            {
                return  ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .cacheControl(CacheControl.noCache())
                        .build();
            }
            else{
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .cacheControl(CacheControl.noCache())
                        .body(assignment);
            }
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .cacheControl(CacheControl.noCache())
                    .build();
        }
    }

    @RequestMapping(value = "/v1/assignments/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@PathVariable String id, @RequestHeader("Authorization") String value,HttpServletRequest request){

        String[] str= value.split(" ");
        byte[] bytes = Base64.getDecoder().decode(str[1].getBytes());
        String st=new String(bytes);
        String[] s =st.split(":");
        if (request.getContentLength() < 0) {
            Assignment assignment = assignmentService.findById(id);
            if (assignment != null) {
                List<JoinKeys> joinKeysList = joinKeysService.findByEmailAndID(s[0], id);
                if (joinKeysList.size() == 0) {
                    return ResponseEntity
                            .status(HttpStatus.FORBIDDEN)
                            .cacheControl(CacheControl.noCache())
                            .build();
                } else {
                    assignmentService.deleteById(id);
                    joinKeysService.deleteById(joinKeysList.get(0).getId());
                    return ResponseEntity
                            .status(HttpStatus.NO_CONTENT)
                            .cacheControl(CacheControl.noCache())
                            .build();
                }

            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .cacheControl(CacheControl.noCache())
                        .build();
            }
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .cacheControl(CacheControl.noCache())
                    .build();
        }

    }

    @RequestMapping(value = "/v1/assignments/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Assignment> updateById(@PathVariable String id,@RequestBody Assignment assignment,@RequestHeader("Authorization") String value){

        String[] str= value.split(" ");
        byte[] bytes = Base64.getDecoder().decode(str[1].getBytes());
        String st=new String(bytes);
        String[] s =st.split(":");
        Assignment temp = assignmentService.findById(id);
        if(temp!=null){
            List<JoinKeys> joinKeysList = joinKeysService.findByEmailAndID(s[0], id);
            if (joinKeysList.size() == 0) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .cacheControl(CacheControl.noCache())
                        .build();
            }
            else
            {
                try {

                    temp.setName(assignment.getName());
                    temp.setPoints(assignment.getPoints());
                    temp.setNum_of_attemps(assignment.getNum_of_attemps());
                    temp.setDeadline(assignment.getDeadline());

                    assignmentService.update(temp);

                }catch (Exception e){
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .cacheControl(CacheControl.noCache())
                            .build();
                }
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .cacheControl(CacheControl.noCache())
                        .build();
            }
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .cacheControl(CacheControl.noCache())
                    .build();
        }
    }

}
