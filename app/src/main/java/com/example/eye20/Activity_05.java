package com.example.eye20;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * 分析报告界面
 * 先以动态表格形式列出一周内的使用数据
 * 然后进行文字综合描述分析
 * 使用数据开始时间：蓝牙发送前存入；结束时间：蓝牙接收后存入
 */
public class Activity_05 extends Activity {
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driverecord_activity);
/*
        @Override
        if(row.getText().length()>0&&column.getText().length()>0){
            //把输入的行和列转为整形
            int row_int=Integer.parseInt(row.getText().toString());
            int col_int=Integer.parseInt(column.getText().toString());
            //获取控件tableLayout
            tableLayout = (TableLayout)findViewById(R.id.table1);
            //清除表格所有行
            tableLayout.removeAllViews();
            //全部列自动填充空白处
            tableLayout.setStretchAllColumns(true);
            //生成X行，Y列的表格
            for(int i=1;i<=row_int;i++)
            {
                TableRow tableRow=new TableRow(MainActivity.this);
                for(int j=1;j<=col_int;j++)
                {
                    //tv用于显示
                    TextView tv=new TextView(MainActivity.this);
                    //Button bt=new Button(MainActivity.this);
                    tv.setText("("+i+","+j+")");
                    tableRow.addView(tv);
                }
                //新建的TableRow添加到TableLayout
                tableLayout.addView(tableRow, new TableLayout.LayoutParams(MP, WC,1));
            }
        }else{
            Toast.makeText(Activity_05.this,"请输入数值",1).show();
        }
    }

 */

 }





    /**
     * 发车数据动态添加状态
     */
    /*
    private void showData(List<CheckedFcRecord> titleData) {
        for (int i = 0; i < titleData.size(); i++) {
            final CheckedFcRecord pojo = titleData.get(i);
            LinearLayout llWashingRoomItem = new LinearLayout(view.getContext());
            llWashingRoomItem.setLayoutParams(new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            llWashingRoomItem = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.checkedfcrecord_template, null);
            TextView time = (TextView) llWashingRoomItem.findViewById(R.id.time);
            TextView vhclNo = (TextView) llWashingRoomItem.findViewById(R.id.vhclNo);
            TextView jpy = (TextView) llWashingRoomItem.findViewById(R.id.jpy);
            TextView ticket = (TextView) llWashingRoomItem.findViewById(R.id.ticket);
            time.setText(DateTools.getStringFromDate(pojo.getFcTime(),null));
            vhclNo.setText(pojo.getVhcl_no());
            jpy.setText(pojo.getJsy_name());
            //Integer类型需要转换用.toString()不然报错
            ticket.setText(pojo.getJps().toString());
            //动态设置layout_weight权重设置表格宽度
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.6f);
            time.setLayoutParams(lp);
            lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            vhclNo.setLayoutParams(lp);
            lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.8f);
            jpy.setLayoutParams(lp);
            lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.8f);
            ticket.setLayoutParams(lp);
            wr_areas.addView(llWashingRoomItem);
        }
    }

     */


}
