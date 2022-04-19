package bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import java.util.Set;

import android.widget.Toast;

public class BlueToothActivity extends Activity {


    private BluetoothAdapter adapter;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //接收蓝牙状态变化的广播
            String action = intent.getAction();
            //发现蓝牙设备
            BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                String name=   device.getName();
            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                //可以在监听到蓝牙状态是进行操作
                switch (device.getBondState()){
                    case BluetoothDevice.BOND_BONDED:
                        //已经配对成功
                        break;
                    case BluetoothDevice.BOND_BONDING:
                        //正在连接
                        break;
                    case BluetoothDevice.BOND_NONE:
                        //取消连接
                        break;
                    default:
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.bluetooth);

        //蓝牙连接设备增加
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        //蓝牙连接状态发生改变
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        //注册
        registerReceiver(receiver, filter);
        //获取蓝牙适配器
        adapter  = BluetoothAdapter.getDefaultAdapter();
        boolean isenable = adapter.isEnabled();
        if(isenable==true)
            System.out.println("蓝牙已开启");
        else
            System.out.println("蓝牙未开启");
        //获取蓝牙是否可用
        Toast.makeText(BlueToothActivity.this, isenable + "<-=-=-=-isenable", Toast.LENGTH_SHORT).show();
        if (!adapter.isEnabled()) {
            //开启蓝牙
            adapter.enable();
        } else {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            //设置你自己的设备可以被其他设备搜索的时间；最大3600
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(intent);
        }
        //蓝牙设备的地址
        String address = adapter.getAddress();
        String name = adapter.getName();
        Log.d("蓝牙", "name=" + name + "  address=" + address);
        //获取通过蓝牙已经连接的设备
        Set<BluetoothDevice> list = adapter.getBondedDevices();
        if (list != null) {
            for (BluetoothDevice device : list) {
                //设备名称
                String dname = device.getName();
            }
        }
        //搜索其他设备
        new Thread() {
            @Override
            public void run() {
                super.run();
                //正在搜索状态
                if (adapter.isDiscovering()) {
                    adapter.cancelDiscovery();
                }
                //开始搜索其他设备；
                adapter.startDiscovery();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}