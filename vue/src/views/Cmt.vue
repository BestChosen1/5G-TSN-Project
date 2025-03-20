<template>
  <div class="home" style ="padding: 10px">
    <h1 style="text-align:center">数据资产列表</h1>
    <!-- 搜索-->
    <div style="margin: 10px 0;">
      <el-form inline="true" size="small" style="font-weight: bold">
        <el-form-item label="资产编号" v-if="user.role == 1" style="margin-right: 20px; margin-left: 200px">
          <el-input v-model="search1" placeholder="请输入资产编号"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search/></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item label="数据类型" style="margin-right: 20px">
          <el-input v-model="search2" placeholder="请输入数据资产类型"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item label="数据持有者" style="margin-right: 20px">
          <el-input v-model="search3" placeholder="请输入数据持有者"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item label="数据名称" style="margin-right: 20px">
          <el-input v-model="search4" placeholder="请输入数据资产名称"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item >

        <el-form-item>
          <el-button type="primary" style="margin-left: 1%" @click="load" size="mini" >
            <svg-icon iconClass="search"/>查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button size="mini"  type="danger" @click="clear">重置</el-button>
        </el-form-item>

      </el-form>
    </div>
    <p><el-button  type="success" size="mini" @click="handleAdd" >上传数据</el-button></p>

<!-- 数据字段-->
    <el-table :data="tableData" stripe border="true"  @selection-change="handleSelectionChange"
              :header-cell-style="{ fontSize: '20px', color: 'black'}"
              :cell-style="{ fontSize: '15px' }"
    >
      <el-table-column v-if="user.role ==1"
                       type="selection"
                       width="55">
      </el-table-column>
      <el-table-column prop="tokenid" label="资产编号"  align="center" min-width="30" sortable
                       :header-cell-style="{ fontSize: '50px' }"
                       :cell-style="{ fontSize: '200px' }"/>
      <el-table-column prop="name" label="数据名称"  align="center" min-width="50" />
      <el-table-column prop="type" label="数据类型" min-width="50" align="center" />
      <el-table-column prop="username" label="数据持有者" min-width="50" align="center" />
      <el-table-column prop="qtype" label="权属类型"  align="center" min-width="60"/>
      <el-table-column fixed="right" label="操作" min-width="50" align="center" style="font-size: 200px">
        <template v-slot="scope">
          <el-button  type="success" size="mini" @click ="handleEdit(scope.row)">管理</el-button>
          <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.cmtId)">
            <template #reference>
              <el-button type="danger" size="mini" >下线</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
<!--    分页-->
    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[13, 20, 30]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total=2196
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>

      <el-dialog v-model="dialogVisible" title="更新数据SFT信息" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="资产编号">
            <el-input style="width: 80%" disabled v-model="form.tokenid"></el-input>
          </el-form-item>
          <el-form-item label="数据名称">
            <el-input style="width: 80%" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="数据类型">
            <el-input style="width: 80%" v-model="form.type"></el-input>
          </el-form-item>
          <el-form-item label="数据持有者">
            <el-input style="width: 80%" v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="权属类型">
            <el-input style="width: 80%" v-model="form.qtype"></el-input>
          </el-form-item>

        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
        </template>
      </el-dialog>

      <!--      添加新团委-->
      <el-dialog v-model="dialogVisible_cmt" title="上传数据资产" width="30%">
        <el-form :model="form_add" label-width="120px">
          <el-form-item label="资产编号">
            <el-input style="width: 80%" v-model="form_add.tokenid"></el-input>
          </el-form-item>
          <el-form-item label="数据名称">
            <el-input style="width: 80%" v-model="form_add.name"></el-input>
          </el-form-item>
          <el-form-item label="数据类型">
            <el-input style="width: 80%" v-model="form_add.type"></el-input>
          </el-form-item>
          <el-form-item label="数据持有者">
            <el-input style="width: 80%" v-model="form_add.username"></el-input>
          </el-form-item>
          <el-form-item label="权属类型">
            <el-input style="width: 80%" v-model="form_add.qtype"></el-input>
          </el-form-item>

        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible_cmt = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
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

export default {
  created(){
    this.load()
    let userStr = sessionStorage.getItem("user") ||"{}"
    this.user = JSON.parse(userStr)
  },
  name: 'User',
  methods: {

    handleSelectionChange(val){
      this.ids = val.map(v => v.id)
    },
    deleteBatch(){
      if (!this.ids.length) {
        ElMessage.warning("请选择数据！")
        return
      }
      //  一个小优化，直接发送这个数组，而不是一个一个的提交删除
      request.post("/cmt/deleteBatch",this.ids).then(res =>{
        if(res.code === '0'){
          ElMessage.success("批量删除成功")
          this.load()
        }
        else {
          ElMessage.error(res.msg)
        }
      })
    },
    load(){
      request.get("cmt/cmtsearch",{
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
      request.delete("cmt/" + id ).then(res =>{
        console.log(res)
        if(res.code == 0 ){
          ElMessage.success("删除成功")
        }
        else
          ElMessage.error(res.msg)
        this.load()
      })
    },


    handleAdd(){
      this.form_add={}
      this.dialogVisible_cmt= true
    },
    // 新增数据
    add(){
      if(this.form_add.tokenid){
        request.post("/cmt/add",this.form_add).then(res =>{
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
          this.dialogVisible_cmt = false
        })
      }
      else {

          ElMessage.error("请输入ID")
          this.load()
          this.dialogVisible_cmt = false
        }
      },



    save(){
      if(this.form.tokenid){
        // console.log(hhh)
        request.put("/cmt",this.form).then(res =>{
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
          this.dialogVisible_cmt = false
        })
      }
      else {
        // console.log(ggh)
        request.post("/cmt",this.form).then(res =>{
          console.log(res)
          if(res.code == 0){
            ElMessage.success('添加成功')
          }
          else {
            ElMessage.error(res.msg)
          }
          this.load()
          this.dialogVisible = false
          this.dialogVisible_cmt = false
        })
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
      form_add: {},
      dialogVisible : false,
      dialogVisible_cmt : false,
      search1:'',
      search2:'',
      search3:'',
      search4:'',
      total:10,
      currentPage:1,
      pageSize: 9,
      tableData: [

      ],
      user:{},
      ids:[],
    }
  },
}
</script>

<style>
  .el-from-item__label {
    color: #3678cf;
  }

</style>