<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<!-- jQuery 2.2.3 -->
<script src="static/plugins/jQuery/jquery-2.2.3.min.js"></script>

<!-- Bootstrap 3.3.7 -->
<script src="static/bootstrap/js/bootstrap.js"></script>

<!-- Sparkline -->
<%--<script src="static/plugins/sparkline/jquery.sparkline.min.js"></script>--%>
<!-- jvectormap -->
<%--<script src="static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>--%>
<!-- jQuery Knob Chart -->
<%--<script src="static/plugins/knob/jquery.knob.js"></script>--%>
<!-- daterangepicker -->
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="static/plugins/daterangepicker/daterangepicker.js"></script>--%>
<!-- datepicker -->
<%--<script src="static/plugins/datepicker/bootstrap-datepicker.js"></script>--%>
<!-- Bootstrap WYSIHTML5 -->
<script src="static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="static/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="static/dist/js/app.min.js"></script>

<!-- AdminLTE for demo purposes -->
<script src="static/dist/js/demo.js"></script>
<script>
    function getFormJson(form) {
        var data = {};
        var formObj = $(form).serializeArray();
        console.log(formObj);
        $.each(formObj,function(){
            if (data[this.name]!==undefined){
                if (!data[this.name].push){
                    data[this.name] = [data[this.name]];
                }
                data[this.name].push(this.value);
            }else{
                data[this.name] = this.value;
            }

        });
        console.log(data);
        return data;
    }

    function initDelId(obj){
        console.log(obj);
        $("#id_box").val($(obj).data("myid"));
        console.log($("#id_box").val());
        return true;
    }
</script>