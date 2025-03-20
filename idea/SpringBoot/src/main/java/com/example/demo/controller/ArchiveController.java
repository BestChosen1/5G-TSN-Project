package com.example.demo.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.Archive;
import com.example.demo.entity.ArchiveBC;
import com.example.demo.mapper.ArchiveMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.fisco.CommitteeTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
@RequestMapping("/archive")
public class ArchiveController {
    @Resource
    ArchiveMapper archiveMapper;

    @PostMapping
    public Result<?> save(@RequestBody Archive archive){
        archiveMapper.insert(archive);
        return Result.success();
    }
//   更新上链
    @PutMapping
    public  Result<?> update(@RequestBody Archive archive) throws Exception{
//        System.out.println(archive);
         LambdaQueryWrapper<Archive> wrappers = Wrappers.<Archive>lambdaQuery();
         wrappers.eq(Archive::getId, archive.getId());
         wrappers.eq(Archive::getIsHistory, 1);
        Archive archive1 = archiveMapper.selectOne(wrappers);
        if (archive1 != null) {
            archive1.setIsHistory(0);
            archiveMapper.update(archive1,wrappers);
        }
        archiveMapper.insert(archive);


//        System.out.println("                123456789 "+archive.myString());
        new CommitteeTest().insertBC(archive.myString());


        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        System.out.println(id);
        archiveMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/getBlockchain")
    public Result<?> getBlockchain(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                               @RequestParam(defaultValue = "") String search2,
                               @RequestParam(defaultValue = "") String search3,
                               @RequestParam(defaultValue = "") String search4,
                               @RequestParam(defaultValue = "") String history1   )
   throws Exception {
//        清空数据表
        archiveMapper.delete(new QueryWrapper<>());
        CommitteeTest committeeTest = new CommitteeTest();
        ArrayList<String> arr = committeeTest.searchBC();
        for (String s : arr) {
//            String  a = " {\"no\":2,\"name\":试试,\"id\":20001234} ";
//            System.out.println("123" + (Archive)arr.get(i));
//            archiveMapper.insert((Archive)arr.get(i));
            //System.out.println("345"+arr.get(i).getClass());
            Gson gson = new Gson();
//            System.out.println(117);
            ArchiveBC archiveBC = gson.fromJson(s, ArchiveBC.class);
//            System.out.println("118");
//            System.out.println("archiveBC" + archiveBC);
            // 中间类转换
            Archive archive = new Archive();

            archive.setNo(archiveBC.getNo());
            archive.setId(archiveBC.getId());

            if (archiveBC.getName() != null)
                archive.setName(archiveBC.getName());
            if (archiveBC.getCollege() != null)
                archive.setCollege(archiveBC.getCollege());
            if (archiveBC.getStuClass() != null)
                archive.setStuClass(archiveBC.getStuClass());
            if (archiveBC.getSex() != null)
                archive.setSex(archiveBC.getSex());
            if (archiveBC.getCardId() != null)
                archive.setCardId(archiveBC.getCardId());
            if (archiveBC.getNation() != null)
                archive.setNation(archiveBC.getNation());
            if (archiveBC.getHome() != null)
                archive.setHome(archiveBC.getHome());
            if (archiveBC.getMemId() != null)
                archive.setMemId(archiveBC.getMemId());
            if (archiveBC.getPhone() != null)
                archive.setPhone(archiveBC.getPhone());
            if (archiveBC.getZhtj() != null)
                archive.setZhtj(archiveBC.getZhtj());
            if (archiveBC.getVolunteer() != null)
                archive.setVolunteer(archiveBC.getVolunteer());
            if (archiveBC.getPetition() != null)
                archive.setPetition(archiveBC.getPetition());
            if (archiveBC.getProblem() != null)
                archive.setProblem(archiveBC.getProblem());
            if (archiveBC.getStatus() != null)
                archive.setStatus(archiveBC.getStatus());
            if (archiveBC.getNotes() != null)
                archive.setNotes(archiveBC.getNotes());
            if (archiveBC.getEditor() != null)
                archive.setEditor(archiveBC.getEditor());
            // 时间类型
            if (archiveBC.getBirth() != null)
                archive.setBirth(Date.from(LocalDate.parse(archiveBC.getBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            if (archiveBC.getJoinDate() != null)
                archive.setJoinDate(Date.from(LocalDate.parse(archiveBC.getJoinDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            archive.setUpdateTime(new Date(Timestamp.valueOf(archiveBC.getUpdateTime()).getTime()));

            archive.setIsHistory(0);
            this.save(archive);

//            System.out.println("archive" + archive);
        }

        List<Archive> archives = archiveMapper.selectList(new QueryWrapper<>());
        Map<Integer, List<Archive>> map = new HashMap<>();
        for (Archive archive : archives) {
//            System.out.println("123" + archive);
            if(!map.containsKey(archive.getId())) {
                map.put(archive.getId(), new ArrayList<>());
            }
            map.get(archive.getId()).add(archive);

        }

//        System.out.println(map);

        for (Integer id : map.keySet()) {
            List<Archive> list = map.get(id);
//            System.out.println("排序前：" + list);
            list.sort(Comparator.comparing(Archive::getUpdateTime));
            list.get(list.size() - 1).setIsHistory(1);
//            System.out.println("排序后：" + list);
            archiveMapper.updateById(list.get(list.size() - 1));
        }

//        int updatedCount = archiveMapper.updateLatestRecordsAsHistory();
//        // 可以根据需要处理updatedCount，比如记录日志等
//        System.out.println("Updated " + updatedCount + " records.");


        LambdaQueryWrapper<Archive> wrappers = Wrappers.<Archive>lambdaQuery();
        if(StringUtils.isNotBlank(search1)){
            wrappers.like(Archive::getCollege,search1);
        }
        if(StringUtils.isNotBlank(search2)){
            wrappers.like(Archive::getStuClass,search2);
        }
        if(StringUtils.isNotBlank(search3)){
            wrappers.like(Archive::getId,search3);
        }
        if(StringUtils.isNotBlank(search4)){
            wrappers.like(Archive::getName,search4);
        }
        if(StringUtils.isNotBlank(history1)){
            wrappers.like(Archive::getIsHistory,1);
        }
        wrappers.orderByAsc(Archive::getId);

//        wrappers.like(Archive::getIsHistory,1);
        Page<Archive> archivePage =archiveMapper.selectPage(new Page<>(pageNum,pageSize), wrappers);
//        System.out.println(wrappers.select());
        return Result.success(archivePage);
    }

    @GetMapping("/archivesearch")
    public Result<?> findHistory1(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                               @RequestParam(defaultValue = "") String search2,
                               @RequestParam(defaultValue = "") String search3,
                               @RequestParam(defaultValue = "") String search4,
                               @RequestParam(defaultValue = "") String history1   )
    {



        LambdaQueryWrapper<Archive> wrappers = Wrappers.<Archive>lambdaQuery();
        if(StringUtils.isNotBlank(search1)){
            wrappers.like(Archive::getCollege,search1);
        }
        if(StringUtils.isNotBlank(search2)){
            wrappers.like(Archive::getStuClass,search2);
        }
        if(StringUtils.isNotBlank(search3)){
            wrappers.like(Archive::getId,search3);
        }
        if(StringUtils.isNotBlank(search4)){
            wrappers.like(Archive::getName,search4);
        }
        if(StringUtils.isNotBlank(history1)){
            wrappers.like(Archive::getIsHistory,1);
        }
//        wrappers.like(Archive::getIsHistory,1);
        Page<Archive> archivePage =archiveMapper.selectPage(new Page<>(pageNum,pageSize), wrappers);
//        System.out.println(wrappers.select());
        return Result.success(archivePage);
    }
        @GetMapping("/findStuHistory")
    public Result<?> findStuHistory(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam() Integer id){
        LambdaQueryWrapper<Archive> wrappers = Wrappers.<Archive>lambdaQuery();
        wrappers.eq(Archive::getId,id);
//            System.out.println(pageNum);
//            System.out.println(pageSize);
        Page<Archive> archivePage =archiveMapper.selectPage(new Page<>(pageNum,pageSize), wrappers);
//        System.out.println(archivePage.getRecords());
//        System.out.println(id);
        return Result.success(archivePage);
    }
}
