const common = (()=>{
    const fmtCurrency = n => '¥' + (Number(n||0).toFixed(2));

    function fillDeptSelect(selectEl){
        selectEl.innerHTML = '<option value="">请选择</option>' +
            MOCK_DEPTS.map(d=> `<option value="${d.deptId}">${d.deptName}</option>`).join('');
        if (layui && layui.form) layui.form.render('select');
    }

    function filterPurchases(list, f){
        return list.filter(it=>{
            const okDept = !f.deptId || it.deptId===f.deptId;
            const okAppl = !f.applicant || it.applicant.includes(f.applicant);
            const okStatus = !f.status || it.status===f.status;
            // 日期范围原型略过严谨处理
            return okDept && okAppl && okStatus;
        });
    }

    // 打开供应商选择
    function openSupplier(trId){
        layui.layer.open({
            type:2,
            title:'选择供应商',
            area:['860px','560px'],
            content:`./supplier_select.html?trId=${trId}`
        });
    }

    // 明细变化计算
    function detailChanged(trId){
        recalcSum();
    }

// 合计：扫描每行 数量×单价
    function recalcSum(){
        const rows = Array.from(document.querySelectorAll('#detailTbody tr'));
        const sum = rows.reduce((acc, tr)=>{
            const qty = Number(tr.querySelector('input[name="qty"]')?.value || 0);
            const price = Number(tr.querySelector('input[name="price"]')?.value || 0);
            return acc + qty * price;
        }, 0);
        const el = document.getElementById('sumAmount');
        if(el) el.textContent = fmtCurrency(sum);
        return sum;
    }

    // 预算校验：合计不得超过预估外购金额
    function validateBudget(){
        const sum = recalcSum();
        const estOutbuyStr = (document.getElementById('estOutbuy')?.value||'').replace(/[¥,\s]/g,'');
        const estOutbuy = Number(estOutbuyStr||0);
        if (estOutbuy>0 && sum > estOutbuy){
            layui.layer.alert(`当前合计 ${fmtCurrency(sum)} 超过预估外购金额 ${fmtCurrency(estOutbuy)}，请调整后再试。`);
            return false;
        }
        return true;
    }

    // 历史采购（根据项目）
    function findHistoryByProject(projectId){
        return MOCK_PURCHASES.filter(p=>p.projectId===projectId);
    }

    // 列表操作
    function submitApply(id){
        layui.layer.msg(`已提交（原型）：${id}`);
    }
    function deleteApply(id){
        layui.layer.confirm(`确认删除 ${id}（原型）？`, ()=> layui.layer.msg('已删除'));
    }

    // 审核
    function approve(id){
        layui.layer.prompt({title:'通过意见（可选）', formType:2}, function(v, idx){
            layui.layer.close(idx);
            layui.layer.msg(`已通过（原型）：${id}`);
        });
    }
    function reject(id){
        layui.layer.prompt({title:'请输入驳回原因（必填）', formType:2}, function(v, idx){
            if(!v || !v.trim()) { layui.layer.msg('驳回原因必填'); return; }
            layui.layer.close(idx);
            layui.layer.msg(`已驳回（原型）：${id}`);
        });
    }

    return {
        fmtCurrency, fillDeptSelect, filterPurchases,
        openSupplier, detailChanged, recalcSum, validateBudget,
        findHistoryByProject, submitApply, deleteApply, approve, reject
    };
})();
