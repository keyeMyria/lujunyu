package com.bm.container.http;

import android.util.Log;
import android.widget.Toast;

import com.bm.container.module.BaseActivity;
import com.bm.container.module.BaseApplication;
import com.bm.container.module.BaseFragment;
import com.bm.container.view.AlertDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author nfmomo
 * <p>
 * 定义Retrofit处理链条中的具体功能
 */

public class ConverterCol {
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(
                    new TypeToken<TreeMap<String, Object>>() {
                    }.getType(),
                    (JsonDeserializer<TreeMap<String, Object>>) (json, typeOfT, context) -> {

                        TreeMap<String, Object> treeMap = new TreeMap<>();
                        JsonObject jsonObject = json.getAsJsonObject();
                        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                        for (Map.Entry<String, JsonElement> entry : entrySet) {
                            treeMap.put(entry.getKey(), entry.getValue());
                        }
                        return treeMap;
                    }).create();

    /**
     * 出错,给提示
     */
    public static Consumer errorToast(BaseActivity activity) {

        return o -> {
            try {
                activity.hideLoading();
                String message = ((Throwable) o).getMessage().toString();
                if (!message.isEmpty()) {
                    Toast.makeText(BaseApplication.context, message, Toast.LENGTH_SHORT).show();
                    Log.e("接口请求异常", message);
                }
            } catch (Exception e) {
                Log.e("接口请求异常", "线程阻塞");
                activity.hideLoading();
            }
        };

    }

    /**
     * 出错,给提示
     */
    public static Consumer errorToast() {
        return o -> {
            try {
                String message = ((Throwable) o).getMessage().toString();
                if (!message.isEmpty()) {
                    Toast.makeText(BaseApplication.context, message, Toast.LENGTH_SHORT).show();
                    Log.e("接口请求异常", message);
                }
            } catch (Exception e) {
                Log.e("接口请求异常", "线程阻塞");
            }
        };
    }

    /**
     * 出错, 弹框提示（多数是后台查询数据为空）
     */
    public static Consumer errorDialog(BaseActivity activity) {
        return o -> {
            try {
                activity.hideLoading();
                String message = ((Throwable) o).getMessage().toString();
                if (!message.isEmpty()) {
                    AlertDialog alert = new AlertDialog.Builder(activity)// builder
                            .setTitle("提示")
                            .setContent(message) //TODO 内容
                            .setPositiveButton("确定", (mdialog, which) -> {
                                mdialog.dismiss();
                            })
                            .create();
                    alert.show();
                    Log.e("接口请求异常", message);
                }
            } catch (Exception e) {
                Log.e("接口请求异常", "线程阻塞");
                activity.hideLoading();
            }
        };
    }

    /**
     * 出错，不给提示（多数是后台查询数据为空）
     */
    public static Consumer error(BaseActivity activity) {
        return o -> {
            try {
                String message = ((Throwable) o).getMessage().toString();
                if (!message.isEmpty()) {
                    activity.hideLoading();
                    Log.e("接口请求异常", message);
                }
            } catch (Exception e) {
                Log.e("接口请求异常", "线程阻塞");
                activity.hideLoading();
            }
        };
    }

    /**
     * 出错，不给提示（多数是后台查询数据为空）
     */
    public static Consumer error(BaseFragment fragment) {
        return o -> {
            try {
                String message = ((Throwable) o).getMessage().toString();
                if (!message.isEmpty()) {
                    fragment.hideLoading();
                    Log.e("接口请求异常", message);
                }
            } catch (Exception e) {
                Log.e("接口请求异常", "线程阻塞");
                fragment.hideLoading();
            }
        };
    }

    /**
     * 出错，不给提示（多数是后台查询数据为空）
     */
    public static Consumer error() {
        return o -> {
            try {
                String message = ((Throwable) o).getMessage().toString();
                if (!message.isEmpty()) {
                    Log.e("接口请求异常", message);
                }
            } catch (Exception e) {
                Log.e("接口请求异常", "线程阻塞");
            }
        };
    }


    /**
     * 第一层json处理 非数组
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Function<BaseBean, T> base(Class<T> clazz) {
        return baseBean -> {
            String status = baseBean.getStatus();
            String message = baseBean.getMsg();
            String data = gson.toJson(baseBean.getData());

            //对请求进行判断
            if (status.equals("0")) {//请求失败
                Log.e("接口请求失败", message);
                throw new RuntimeException(message);
            } else {//请求成功
                Log.e("接口请求成功", data);
                if (data.equals("null") || data.isEmpty()) {
                    return clazz.newInstance();
                } else {
                    data = data.replaceAll(":null", ":\"\"");
                    data = data.replaceAll("\\[null,", "\\[");
                    data = data.replaceAll("\\[null]", "\\[\\]");
                    data = data.replaceAll(",null,", ",");
                    data = data.replaceAll(",null\\]", "\\]");
                    return gson.fromJson(data, clazz);
                }
            }
        };
    }

    /**
     * 第一层json处理 数组
     *
     * @param clazz
     * @param <T>
     * @return
     */

    public static <T> Function<BaseBean, List<T>> baseList(Class<T[]> clazz) {
        return baseBean -> {
            String status = baseBean.getStatus();
            String message = baseBean.getMsg();
            String data = gson.toJson(baseBean.getData());

            //对请求进行判断
            if (status.equals("0")) {//请求失败
                Log.e("接口请求失败", "message");
                throw new RuntimeException(message);
            } else {//请求成功
                Log.e("接口请求成功", data);
                if (data.equals("null") || data.isEmpty()) {
                    return new ArrayList<T>();
                } else {
                    data = data.replaceAll(":null", ":\"\"");
                    data = data.replaceAll("\\[null,", "\\[");
                    data = data.replaceAll("\\[null]", "\\[\\]");
                    data = data.replaceAll(",null,", ",");
                    data = data.replaceAll(",null\\]", "\\]");
                    T[] arr = gson.fromJson(data, clazz);
                    return Arrays.asList(arr);
                }
            }
        };
    }

    /**
     * 第一层json处理,不对status进行判断
     * 后台erp特别需求，为求走通流程，临时的筛选器
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Function<BaseBean, T> noJudge(Class<T> clazz) {
        return baseBean -> {
            String status = baseBean.getStatus();
            String message = baseBean.getMsg();
            String data = gson.toJson(baseBean.getData());

            Log.e("接口请求成功", data);
            if (data.equals("null") || data.isEmpty()) {
                return clazz.newInstance();
            } else {
                data = data.replaceAll(":null", ":\"\"");
                data = data.replaceAll("\\[null,", "\\[");
                data = data.replaceAll("\\[null]", "\\[\\]");
                data = data.replaceAll(",null,", ",");
                data = data.replaceAll(",null\\]", "\\]");
                return gson.fromJson(data, clazz);
            }
        };
    }

    /**
     * 出错,给提示
     */
    public static Action completeWithLoading(BaseActivity activity) {
        return () -> {
            try {
                activity.hideLoading();
            } catch (Exception e) {
                activity.hideLoading();
                Log.e("接口请求异常", "线程阻塞");
            }
        };
    }

    /**
     * 出错,给提示
     */
    public static Action completeWithLoading(BaseFragment fragment) {
        return () -> {
            try {
                fragment.hideLoading();
            } catch (Exception e) {
                fragment.hideLoading();
                Log.e("接口请求异常", "线程阻塞");
            }
        };
    }

}
