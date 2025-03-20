<template>
  <div class="home" style ="padding: 10px; width: 100%" >
    <!-- 搜索-->
    <div style="margin: 10px 0;">
      <el-form inline="true" size="small">
        <el-form-item label="学院" v-if="user.role == 1">
          <el-input v-model="search1" placeholder="请输入学院编号"  clearable>
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
          <el-button type="primary" style="margin-left: 1%" @click="load" size="mini" >
            <svg-icon iconClass="search"/>查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button size="mini"  type="danger" @click="clear">重置</el-button>
        </el-form-item>

      </el-form>
    </div>
    <!-- 按钮-->
    <div style="margin: 10px 0;" >
      <el-button type="primary" @click = "add" >新增档案</el-button>

    </div>
    <!-- 数据字段-->
    <div style="overflow:auto">
      <el-table :data="tableData" style="width:100%"  :default-sort = "{prop: 'id', order: 'ascending'}" stripe border="true" @selection-change="handleSelectionChange">
<!--        <el-table-column v-if="user.role ==1"-->
<!--                         type="selection"-->
<!--                         width="55">-->
<!--        </el-table-column>-->

<!--        <el-table-column prop= label="序号" sortable />-->
        <el-table-column prop="id" fixed label="学号"  width="93" sortable />
        <el-table-column prop="name" fixed label="姓名" width="58" />
        <el-table-column prop="college" label="学院" min-width="50" />
        <el-table-column prop="stuClass" label="班级" min-width="88" sortable />
        <el-table-column prop="sex" label="性别" min-width="45" />

        <el-table-column prop="cardId" label="身份证号码" min-width="148" />
        <el-table-column prop="nation" label="民族"  min-width="45" />
        <el-table-column prop="home" label="生源地"  min-width="80" />

        <el-table-column prop="birth" label="出生日期"  min-width="92" sortable />
        <el-table-column prop="joinDate" label="入团日期" min-width="92"  sortable />
        <el-table-column prop="memId" label="团员证编号" min-width="80"  />
        <el-table-column prop="phone" label="手机号码"  min-width="100" />

        <el-table-column prop="zhtj" label="智慧团建" width="76" >
          <template v-slot="scope">
            <el-tag v-if="scope.row.zhtj == 0" type="warning">未录入</el-tag>
            <el-tag v-else type="success">已录入</el-tag>
          </template>
        </el-table-column>

        <el-table-column prop="volunteer" label="志愿书" width="57" >
          <template v-slot="scope">
            <el-tag v-if="scope.row.volunteer == 0" type="warning">无</el-tag>
            <el-tag v-else type="success">有</el-tag>
          </template>
        </el-table-column>

        <el-table-column prop="petition" label="申请书" width="57" >
<!--             :filters="[{ text: '有', value: '1' }, { text: '无', value: '0' }]"-->
<!--            :filter-method="filterTag"-->
<!--              filter-placement="bottom-end"https://element.eleme.io/#/zh-CN/component/table-->

          <template v-slot="scope">
            <el-tag v-if="scope.row.petition == 0" type="warning">无</el-tag>
            <el-tag v-else type="success">有</el-tag>
          </template>
        </el-table-column>

        <el-table-column prop="problem" label="问题档案" width="88" >
          <template v-slot="scope">
            <el-tag v-if="scope.row.problem == 0" type="warning">问题档案</el-tag>
            <el-tag v-else type="success">正常</el-tag>
          </template>
        </el-table-column>

        <el-table-column prop="status" label="状态" width="63" >
          <template v-slot="scope">
            <el-tag v-if="scope.row.status == 0" type="warning">毕业</el-tag>
            <el-tag v-else-if="scope.row.status == 1" type="success">正常</el-tag>
            <el-tag v-else-if="scope.row.status == 2" type="warning">入伍</el-tag>
            <el-tag v-else-if="scope.row.status == 3" type="warning">休学</el-tag>
            <el-tag v-else-if="scope.row.status == 4" type="warning">退学</el-tag>
            <el-tag v-else type="warning">其他</el-tag>
          </template>
        </el-table-column>

        <el-table-column prop="notes" label="备注"  min-width="45" />
        <el-table-column prop="editor" label="编辑者" min-width="57" />
        <el-table-column prop="updateTime" label="编辑时间"  min-width="92" sortable/>


        <!--      <template v-slot="scope" v-if="tableData. == 1">-->
        <el-table-column min-width="140"  fixed="right" label="操作">
          <template v-slot="scope">
<!--            <div style="display: flex; flex-direction: column; align-items: center;">-->
            <el-button  :disabled="scope.row.isHistory == 0" size="mini" @click ="handleEdit(scope.row)" >编辑</el-button>

            <el-button  size="mini" @click ="handleHistory(scope.row.id)" >历史</el-button>
<!--            </div>-->
          </template>
        </el-table-column>
        <!--        </template>-->
      </el-table>
    </div>

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

      <el-dialog v-model="dialogVisible" title="新增档案" width="30%">
        <el-form :model="form" label-width="120px">

          <el-form-item label="学号">
            <el-input  style="width: 80%" v-model="form.id"></el-input>
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
          <el-form-item label="身份证号码">
            <el-input style="width: 80%" v-model="form.cardId"></el-input>
          </el-form-item>
          <el-form-item label="民族">
            <el-input style="width: 80%" v-model="form.nation"></el-input>
          </el-form-item>
          <el-form-item label="生源地">
            <el-input style="width: 80%" v-model="form.home"></el-input>
          </el-form-item>

          <el-form-item label="出生日期">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.birth" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="入团日期">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.joinDate" ></el-date-picker>
            </div>
          </el-form-item>

          <el-form-item label="团员证编号">
            <el-input style="width: 80%" v-model="form.memId"></el-input>
          </el-form-item>
          <el-form-item label="手机号码">
            <el-input style="width: 80%" v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="智慧团建">
            <el-radio v-model="form.zhtj" label="1">已录入</el-radio>
            <el-radio v-model="form.zhtj" label="0">未录入</el-radio>
          </el-form-item>
          <el-form-item label="入团志愿书">
            <el-radio v-model="form.volunteer" label="1">有</el-radio>
            <el-radio v-model="form.volunteer" label="0">无</el-radio>
          </el-form-item>
          <el-form-item label="入团申请书">
            <el-radio v-model="form.petition" label="1">有</el-radio>
            <el-radio v-model="form.petition" label="0">无</el-radio>
          </el-form-item>
          <el-form-item label="问题档案">
            <el-radio v-model="form.problem" label="1">正常</el-radio>
            <el-radio v-model="form.problem" label="0">问题档案</el-radio>
          </el-form-item>

          <el-form-item label="学生状态">
            <el-select v-model="form.status" placeholder="请选择">
              <el-option
                  v-for="item in options_status"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
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

      <el-dialog v-model="dialogVisible2" title="编辑档案信息" width="30%">
        <el-form  :model="form"  label-width="120px">

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
          <el-form-item label="身份证号码">
            <el-input style="width: 80%" v-model="form.cardId"></el-input>
          </el-form-item>
          <el-form-item label="民族">
            <el-input style="width: 80%" v-model="form.nation"></el-input>
          </el-form-item>
          <el-form-item label="生源地">
            <el-input style="width: 80%" v-model="form.home"></el-input>
          </el-form-item>

          <el-form-item label="出生日期">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.birth" ></el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="入团日期">
            <div>
              <el-date-picker value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable v-model="form.joinDate" ></el-date-picker>
            </div>
          </el-form-item>

          <el-form-item label="团员证编号">
            <el-input style="width: 80%" v-model="form.memId"></el-input>
          </el-form-item>
          <el-form-item label="手机号码">
            <el-input style="width: 80%" v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="智慧团建">
            <el-radio v-model="form.zhtj" label="1">已录入</el-radio>
            <el-radio v-model="form.zhtj" label="0">未录入</el-radio>
          </el-form-item>
          <el-form-item label="入团志愿书">
            <el-radio v-model="form.volunteer" label="1">有</el-radio>
            <el-radio v-model="form.volunteer" label="0">无</el-radio>
          </el-form-item>
          <el-form-item label="入团申请书">
            <el-radio v-model="form.petition" label="1">有</el-radio>
            <el-radio v-model="form.petition" label="0">无</el-radio>
          </el-form-item>
          <el-form-item label="问题档案">
            <el-radio v-model="form.problem" label="1">正常</el-radio>
            <el-radio v-model="form.problem" label="0">问题档案</el-radio>
          </el-form-item>

          <el-form-item label="学生状态">
            <el-select v-model="form.status" placeholder="请选择">
              <el-option
                  v-for="item in options_status"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
                    <el-form-item label="备注">
            <el-input style="width: 80%" v-model="form.notes"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
        <span class="dialog-footer">
        <el-button @click="dialogVisible2 = false">取 消</el-button>
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
import moment from "moment";
export default {
  created(){
    let userStr = sessionStorage.getItem("user") ||"{}"
    this.user = JSON.parse(userStr)
    this.load()
  },
  name: 'Book',
  methods: {
    // (this.isbnArray.indexOf(scope.row.isbn)) == -1
    handleSelectionChange(val){
      this.ids = val.map(v =>v.id)
    },
    deleteBatch(){
      if (!this.ids.length) {
        ElMessage.warning("请选择数据！")
        return
      }
      //  一个小优化，直接发送这个数组，而不是一个一个的提交删除
      request.post("/book/deleteBatch",this.ids).then(res =>{
        if(res.code === '0'){
          ElMessage.success("批量删除成功")
          this.load()
        }
        else {
          ElMessage.error(res.msg)
        }
      })
    },
    load() {
      if (this.user.role == 2) {
        this.search1 = this.user.cmtId
      }
      this.numOfOutDataBook = 0;
      this.outDateBook = [];
      request.get("/archive/getBlockchain", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search1: this.search1,
          search2: this.search2,
          search3: this.search3,
          search4: this.search4,
          history1: '1'
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })

    },
    clear() {
      this.search1 = ""
      this.search2 = ""
      this.search3 = ""
      this.load()
    },

    handleDelete(id) {
      request.delete("book/" + id).then(res => {
        console.log(res)
        if (res.code == 0) {
          ElMessage.success("删除成功")
        } else
          ElMessage.error(res.msg)
        this.load()
      })
    },

    // 档案编辑历史
    handleHistory(id) {
      request.get("/archive/findStuHistory", {
        params: {
          id: id
        }
      }).then(res => {
        console.log(res)
        this.tableData = {}
        this.currentPage = 1
        this.tableData = res.data.records
        this.total = res.data.total
        this.tableData.isHistory = res.data.records.isHistory
      })
    },
    add() {
      this.dialogVisible = true
      this.form = {}
    },
    // 新增或编辑档案
    save() {
      if (!this.form.birth) {
        ElMessage.error("请输入出生日期");
        return
      }
       if (!this.form.joinDate) {
        ElMessage.error("请输入入团日期");
        return
      }
      if (this.form.id) {
        request.get("/archive/archivesearch", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,

          }
        }).then(res => {
          console.log(res)
          this.tableData = res.data.records
          this.total = res.data.total
          this.form.no = this.total + 1
          this.form.editor = this.user.id
          this.form.updateTime = moment(new Date()).format("yyyy-MM-DD HH:mm:ss")
          this.form.isHistory = 1
// console.log(this.form.no)
          request.put("/archive", this.form).then(res => {
            console.log(res)
            if (res.code == 0) {
              ElMessage({
                message: '操作成功',
                type: 'success',
              })
            } else {
              ElMessage.error(res.msg)
            }
          })
          this.dialogVisible = false
          this.dialogVisible2 = false
          this.load()

        })
      } else {
        ElMessage.error("请输入学号")
      }
    },


    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible2 = true
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },

  },
  data() {
    return {
      form: {},
      rules: {
        phone: [
          {
            min: 11,
            max: 11,
            message: '长度要求为2到13位',
            trigger: 'blur',
          },
        ]

      },
      dialogVisible: false,
      dialogVisible2: false,
      search1: '',
      search2: '',
      search3: '',
      search4: '',
      total: 10,
      currentPage: 1,
      pageSize: 10,
      tableData: [],
      user: {},
      number: 0,
      bookData: [],
      isbnArray: [],
      outDateBook: [],
      numOfOutDataBook: 0,

      options_status: [{
        value: '0',
        label: '毕业'
      }, {
        value: '1',
        label: '正常'
      }, {
        value: '2',
        label: '入伍'
      }, {
        value: '3',
        label: '休学'
      }, {
        value: '4',
        label: '退学'
      }, {
        value: '5',
        label: '其他'
      }],
    }
  },
}
</script>