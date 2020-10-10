<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="id">
            <el-input v-model="form.id" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="稿件描述" prop="desc">
            <el-input v-model="form.desc" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="稿件id" prop="employId">
            <el-input v-model="form.employId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="状态 0表示没有验收 1表示验收" prop="status">
            <el-input v-model="form.status" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="交稿威客id" prop="uid">
            <el-input v-model="form.uid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="文件后缀" prop="type">
            <el-input v-model="form.type" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建时间" prop="createdAt">
            <el-input v-model="form.createdAt" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column v-if="columns.visible('id')" prop="id" label="id" />
        <el-table-column v-if="columns.visible('desc')" prop="desc" label="稿件描述" />
        <el-table-column v-if="columns.visible('employId')" prop="employId" label="稿件id" />
        <el-table-column v-if="columns.visible('status')" prop="status" label="状态 0表示没有验收 1表示验收" />
        <el-table-column v-if="columns.visible('uid')" prop="uid" label="交稿威客id" />
        <el-table-column v-if="columns.visible('type')" prop="type" label="文件后缀" />
        <el-table-column v-if="columns.visible('createdAt')" prop="createdAt" label="创建时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createdAt) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-permission="['admin','zbEmployWork:edit','zbEmployWork:del']" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudZbEmployWork from '@/api/zbEmployWork'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'
import MaterialList from "@/components/material";

// crud交由presenter持有
const defaultCrud = CRUD({ title: '交稿记录', url: 'api/zbEmployWork', sort: 'id,desc', crudMethod: { ...crudZbEmployWork }})
const defaultForm = { id: null, desc: null, employId: null, status: null, uid: null, type: null, createdAt: null }
export default {
  name: 'ZbEmployWork',
  components: { pagination, crudOperation, rrOperation, udOperation ,MaterialList},
  mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
  data() {
    return {
      
      permission: {
        add: ['admin', 'zbEmployWork:add'],
        edit: ['admin', 'zbEmployWork:edit'],
        del: ['admin', 'zbEmployWork:del']
      },
      rules: {
        desc: [
          { required: true, message: '稿件描述不能为空', trigger: 'blur' }
        ],
        employId: [
          { required: true, message: '稿件id不能为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '状态 0表示没有验收 1表示验收不能为空', trigger: 'blur' }
        ],
        uid: [
          { required: true, message: '交稿威客id不能为空', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '文件后缀不能为空', trigger: 'blur' }
        ],
        createdAt: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ]
      }    }
  },
  watch: {
  },
  methods: {
    // 获取数据前设置好接口地址
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }, // 新增与编辑前做的操作
    [CRUD.HOOK.afterToCU](crud, form) {
    },
  }
}



</script>

<style scoped>
  .table-img {
    display: inline-block;
    text-align: center;
    background: #ccc;
    color: #fff;
    white-space: nowrap;
    position: relative;
    overflow: hidden;
    vertical-align: middle;
    width: 32px;
    height: 32px;
    line-height: 32px;
  }
</style>
