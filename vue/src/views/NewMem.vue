<template>
  <div class="home" style ="padding: 10px">
    <!-- 按钮-->
    <!-- 搜索-->
    <div style="margin: 10px 0;">
      <el-form inline="true" size="small">
        <el-form-item label="学院" v-if="user.role == 1" >
          <el-input v-model="search1"  placeholder="请输入学院编号"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search/></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item label="专业班级" >
          <el-input v-model="search2" placeholder="请输入专业班级"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item label="学号" >
          <el-input v-model="search3" placeholder="请输入学号"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item label="姓名" >
          <el-input v-model="search4" placeholder="请输入姓名"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item>
          <el-button type="primary" style="margin-left: 1%" @click="load" size="mini">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button size="mini"  type="danger" @click="clear">重置</el-button>
        </el-form-item>
        <el-form-item>
          <el-button size="mini"  type="success" @click="handleAdd">新增</el-button>
        </el-form-item>
        <el-form-item>
          <el-popconfirm title="确认删除?" @confirm="deleteBatch" v-if="user.role == 1">
            <template #reference>
              <el-button  type="danger" size="mini" >批量删除</el-button>
            </template>
          </el-popconfirm>
        </el-form-item>
      </el-form>


    </div>

    <!--    <div style="margin: 10px 0;" >-->

    <!--    </div>-->
    <!-- 数据字段-->
    <el-table :data="tableData" stripe border="true"  @selection-change="handleSelectionChange" >
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>

      <el-table-column prop="id" fixed label="学号" min-width="93" sortable />
      <el-table-column prop="name" fixed label="姓名" min-width="58" />
      <el-table-column prop="college" label="学院" min-width="50" />
      <el-table-column prop="stuClass" label="班级" min-width="88" sortable />
      <el-table-column prop="sex" label="性别" min-width="45"/>
      <el-table-column prop="phone" fixed label="手机号码" min-width="105"/>
      <el-table-column prop="birth" label="出生日期" min-width="92" sortable/>
      <el-table-column prop="petition" label="申请书递交" min-width="92" sortable/>
      <el-table-column prop="active" label="入团积极分子" min-width="92" sortable/>
      <el-table-column prop="dev" label="发展对象" min-width="92" sortable/>
      <el-table-column prop="talk" label="发展对象谈话" min-width="92" sortable/>
      <el-table-column prop="joinDate" label="入团日期" min-width="92" sortable/>
      <el-table-column prop="notes" label="备注" min-width="60"/>
      <el-table-column prop="editor" label="编辑者" min-width="57"/>
      <el-table-column prop="updateTime" label="编辑时间" min-width="123" sortable/>



      <el-table-column fixed="right" label="操作" min-width="140">

        <template v-slot="scope">
          <el-button  :disabled="scope.row.role == 1" size="mini" @click ="handleEdit(scope.row)">编辑</el-button>

          <el-popconfirm  title="确认删除?" @confirm="handleDelete(scope.row.id)">
            <template  #reference>
              <el-button :disabled="scope.row.role == 1" type="danger" size="mini" >删除</el-button>
            </template>
          </el-popconfirm>

        </template>

      </el-table-column>
    </el-table>
    <!--    分页-->
    <div style="margin: 10px 0">

      <el-pagination

          v-model:currentPage="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >

      </el-pagination>

      <el-dialog v-model="dialogVisible_add" title="新增发展记录" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="学号">
            <el-input style="width: 80%" v-model="form.id"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input style="width: 80%" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="学院">
            <el-input style="width: 80%" v-model="form.college"></el-input>
          </el-form-item>
          <el-form-item label="班级">
            <el-input style="width: 80%" v-model="form.stuClass"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-input style="width: 80%" v-model="form.sex"></el-input>
          </el-form-item>
          <el-form-item label="手机号码">
            <el-input style="width: 80%" v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="出生日期">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.birth" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="申请书递交">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.petition" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="入团积极分子">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.activie" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="发展对象">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.dev" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="发展对象谈话">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.talk" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="入团日期">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.joinDate" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="备注">
            <el-input style="width: 80%" v-model="form.notes"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible_add = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </span>
        </template>
      </el-dialog>

      <el-dialog v-model="dialogVisible" title="编辑发展记录" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="学号">
            <el-input disabled style="width: 80%" v-model="form.id"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input style="width: 80%" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="学院">
            <el-input style="width: 80%" v-model="form.college"></el-input>
          </el-form-item>
          <el-form-item label="班级">
            <el-input style="width: 80%" v-model="form.stuClass"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-input style="width: 80%" v-model="form.sex"></el-input>
          </el-form-item>
          <el-form-item label="手机号码">
            <el-input style="width: 80%" v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="出生日期">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.birth" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="申请书递交">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.petition" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="入团积极分子">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.active" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="发展对象">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.dev" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="发展对象谈话">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.talk" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="入团日期">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.joinDate" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="备注">
            <el-input style="width: 80%" v-model="form.notes"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import request from "../utils/request";
import {ElMessage} from "element-plus";
import moment from "moment/moment";

export default {
  created(){

    let userStr = sessionStorage.getItem("user") ||"{}"
    this.user = JSON.parse(userStr)
    this.load()
  },
  name: 'User',
  methods: {
    handleSelectionChange(val){
      this.ids = val.map(v => v.id)
    },

    load(){
      if (this.user.role == 2) {
        this.search1 = this.user.cmtId
      }
      request.get("newMem/newMemsearch",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search1: this.search1,
          search2: this.search2,
          search3: this.search3,
          search4: this.search4,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    clear(){
      this.search1 = ""
      this.search2 = ""
      this.search3 = ""
      this.search4 = ""
      this.load()
    },

    handleDelete(id){
      request.delete("newMem/" + id ).then(res =>{
        console.log(res)
        if(res.code == 0 ){
          ElMessage.success("删除成功")
        }
        else
          ElMessage.error(res.msg)
        this.load()
      })
    },
    deleteBatch(){
      if (!this.ids.length) {
        ElMessage.warning("请选择数据！")
        return
      }

      request.post("/newMem/deleteBatch",this.ids).then(res =>{
        if(res.code === '0'){
          ElMessage.success("批量删除成功")
          this.load()
        }
        else {
          ElMessage.error(res.msg)
        }
      })
    },

    handleAdd(){
      this.dialogVisible_add= true
      this.form ={}
    },
    // 新增数据
    add(){
      if(this.form.id){
        this.form.editor = this.user.id
        this.form.updateTime = moment(new Date()).format("yyyy-MM-DD HH:mm:ss")
        request.post("/newMem/add",this.form).then(res =>{
          console.log(res)
          if(res.code == 0){
            ElMessage({
              message: '添加成功',
              type: 'success',
            })

          }
          else {
            ElMessage.error(res.msg)
          }

          this.load()
          this.dialogVisible_add = false
        })
      }
      else {

        ElMessage.error("请输入ID")

      }
    },
    save(){
      if(this.form.id){
        this.form.editor = this.user.id
        this.form.updateTime = moment(new Date()).format("yyyy-MM-DD HH:mm:ss")
        request.put("/newMem",this.form).then(res =>{
          console.log(res)
          if(res.code == 0){
            ElMessage({
              message: '更新成功',
              type: 'success',
            })
          }
          else {
            ElMessage.error(res.msg)
          }

          this.load()
          this.dialogVisible = false

        })
      }
      else {
        ElMessage.error('请输入学号！')
      }

    },


    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    },
  },
  data() {
    return {
      form: {},

      dialogVisible : false,
      dialogVisible_add : false,
      search1:'',
      search2:'',
      search3:'',
      search4:'',
      total:10,
      currentPage:1,
      pageSize: 10,
      tableData: [

      ],
      user:{},
      ids:[],
    }
  },
}
</script>
