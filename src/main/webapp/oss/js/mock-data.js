// 部门
const MOCK_DEPTS = [
    {deptId:'D001', deptName:'研发部'},
    {deptId:'D002', deptName:'市场部'},
    {deptId:'D003', deptName:'财务部'}
];

// 项目（预估外购金额 = 预估产值 * 0.4）
const MOCK_PROJECTS = [
    {projectId:'P-1001', projectName:'ERP 升级项目', partner:'华信科技', deptId:'D001', deptName:'研发部', estOutput: 1200000.00, estOutbuy: 1200000.00 * 0.4},
    {projectId:'P-1002', projectName:'数据中台建设', partner:'星图数据', deptId:'D001', deptName:'研发部', estOutput: 800000.00, estOutbuy: 800000.00 * 0.4},
    {projectId:'P-2001', projectName:'品牌官网改版', partner:'远航互动', deptId:'D002', deptName:'市场部', estOutput: 300000.00, estOutbuy: 300000.00 * 0.4}
];

// 供应商
const MOCK_SUPPLIERS = [
    {supplierCode:'S001', supplierName:'云捷软件', category:'software', linkman:'孙丽', phone:'13800001111', address:'上海市浦东新区张江路 88 号'},
    {supplierCode:'S002', supplierName:'宏科硬件', category:'hardware', linkman:'王强', phone:'13900002222', address:'深圳市南山区科苑大道 66 号'},
    {supplierCode:'S003', supplierName:'至臻服务', category:'service',  linkman:'李敏', phone:'13700003333', address:'北京市朝阳区建国门外大街 18 号'}
];

// 采购申请（3 条示例）
const MOCK_PURCHASES = [
    {id:'A1', applyNo:'CG-202509-0001', applicant:'张三', deptId:'D001', deptName:'研发部', projectId:'P-1001', projectName:'ERP 升级项目',
        applyTime:'2025-09-10 10:20:00', sumAmount: 120000.00, status:'draft', statusText:'草稿', remark:'——'},
    {id:'A2', applyNo:'CG-202509-0002', applicant:'李四', deptId:'D002', deptName:'市场部', projectId:'P-2001', projectName:'品牌官网改版',
        applyTime:'2025-09-12 09:00:00', sumAmount: 60000.00, status:'submitted', statusText:'已提交', remark:'待部门审核'},
    {id:'A3', applyNo:'CG-202509-0003', applicant:'王五', deptId:'D001', deptName:'研发部', projectId:'P-1002', projectName:'数据中台建设',
        applyTime:'2025-09-13 14:33:00', sumAmount: 90000.00, status:'dept_approving', statusText:'部门审核中', remark:'——'}
];
window.initPurchaseListUI = function(){
    const $ = layui.$, laydate = layui.laydate, form = layui.form, table = layui.table;

    // 更多查询折叠
    $('#moreToggle').on('click', function(){
        const box = $('#moreBox'); const open = box.is(':visible');
        box;
        this.textContent = open ? '更多查询 ▾' : '收起更多 ▴';
    });

    // 日期选择
    laydate.render({elem:'#dateFrom'});
    laydate.render({elem:'#dateTo'});

    // 部门下拉
    common.fillDeptSelect(document.getElementById('deptId'));

    // 表格
    table.render({
        elem:'#tablePurchase',
        height: 'full-260',
        data: MOCK_PURCHASES,
        page: true,
        cols:[[
            {type:'numbers', title:'序号', width:60},
            {field:'applyNo', title:'申请单编号', width:180},
            {field:'projectName', title:'项目名称', minWidth:200},
            {field:'deptName', title:'所属部门', width:120},
            {field:'applicant', title:'申请人', width:100},
            {field:'applyTime', title:'申请时间', width:160},
            {field:'sumAmount', title:'预估金额', width:120, templet:d=>common.fmtCurrency(d.sumAmount)},
            {field:'sumAmount', title:'建议采购金额', width:140, templet:d=>common.fmtCurrency(d.sumAmount)}, /* 原型同值 */
            {title:'操作', width:240, templet:d=>`
        <div class="op-btns">
          <a class="layui-btn layui-btn-xs" href="./purchase_form.html?id=${d.id}">编辑</a>
          <button class="layui-btn layui-btn-normal layui-btn-xs" onclick="common.submitApply('${d.id}')">提交</button>
          <button class="layui-btn layui-btn-danger layui-btn-xs" onclick="common.deleteApply('${d.id}')">删除</button>
        </div>`}
        ]]
    });
};