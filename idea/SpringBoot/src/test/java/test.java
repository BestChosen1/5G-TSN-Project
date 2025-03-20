
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Archive;
import com.example.demo.mapper.ArchiveMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.Timestamp;
public class test {
@Test
public void test(){
//    String a = "2023-06-06";
//
//    Timestamp timestamp = Timestamp.valueOf("2023-06-06 21:39:52");
//		long time = timestamp.getTime();
//		Date date = new Date(time);

//    System.out.println(Date.from(LocalDate.parse(a, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(ZoneId.systemDefault()).toInstant()));
//最后一个时间必须有引号
//{no:2, id:123456, name:张三, college:05, stuClass:物联网2002, sex:1, cardId:12302139123213, nation:汉族, home:河北宣化, birth:2003-06-21, joinDate:2015-06-21, memId:null, phone:12389323443, zhtj:1, volunteer:0, petition:1, problem:0, status:1, notes:null, editor:1, updateTime:'2023-06-21 07:39:07'}
//{'no':2, 'id':123456, 'name':张三, 'college':05, 'stuClass':物联网2002, 'sex':1, 'cardId':12302139123213, 'nation':汉族, 'home':河北宣化, 'birth':2003-06-21, 'joinDate':2015-06-21, 'memId':null, 'phone':12389323443, 'zhtj':1, 'volunteer':0, 'petition':1, 'problem':0, 'status':1, 'notes':null, 'editor':1, 'updateTime':'2023-06-21 07:39:07'}
//{'no':1,'id':123,'name':'ewad', 'sex':1, 'cardId':'12302139123213', 'nation':'汉族', 'home':'河北宣化', 'birth':'2003-06-21', 'joinDate':'2015-06-21','updateTime':'2024-01-21 04:39:07','isHistory':1}
//{'no':1,'id':123,'name':'ewad','updateTime':'2024-01-21 04:39:07','isHistory':1}
    }
}
