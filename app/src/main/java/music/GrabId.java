package music;

import java.util.List;

/**
 * 音乐模块从网易云接口通过id获取音乐数据
 * 接口：https://music.163.com/#/search/m/?id=28193075&s=%E4%B8%83%E5%8F%8B&type=1
 * 待完善
 */
public class GrabId {


    /** 搜索关键字地址 */
    //public static String KEY_SEARCH_URL = "http://www.xiami.com/search/song?key=";
    /** ID接口地址 */
    //public static String ID_SEARCH_URL = "http://www.xiami.com/song/playlist/id/";

    /**
     * 抓取歌曲id
     *
     * @param 搜索关键词
     * @param listener
     *            完成监听
     */

    /*
    public static void getIds(String input, OnLoadSearchFinishListener listener) {
        List<String> allIds = new ArrayList<String>();
        String key = deCondeKey(input);// 解析用户输入关键字为 UTF-8
        Document document = null;
        try {
            document = Jsoup.connect(KEY_SEARCH_URL + key).get();// jsoup连接最终拼接而成的请求字符串
            Elements elements = document.getElementsByClass("track_list");// 选择类标签
            if (elements.size() != 0) {
                Elements all = elements.get(0).getElementsByClass("chkbox");
                int size = all.size();
                for (int i = 0; i < size; i++) {
                    String id = all.get(i).select("input").attr("value");
                    if (!StringUtils.isEmpty(id)) {
                        allIds.add(id);// 不为空的话加入id list中，便于初次抓取完以后统一请求
                    }
                }
                if (listener != null) {
                    if (allIds.size() == 0) {
                        listener.onLoadFiler();// id list大小为0 说明没有获取到数据，抓取失败
                    } else {
                        // 统一请求id接口地址进行再次抓取
                        listener.onLoadSucess(getOnlineSearchList(allIds));
                    }
                }
            }

        } catch (IOException e) {
            listener.onLoadFiler();
            e.printStackTrace();
        }
    }


     */
}
