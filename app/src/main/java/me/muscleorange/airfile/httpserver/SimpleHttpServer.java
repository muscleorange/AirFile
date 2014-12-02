package me.muscleorange.airfile.httpserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.muscleorange.airfile.utils.FileExtraUtils;

/**
 * Created by muscleorange on 14/11/30.
 */
public class SimpleHttpServer extends NanoHTTPD {

    private final String webRoot;

    public SimpleHttpServer(String webRoot, int port){
        super(port);
        this.webRoot = webRoot;
    }

    @Override
    public Response serve(IHTTPSession session){
        Method method = session.getMethod();
        if(Method.POST.equals(method)){
            doPost(session);
        }
        return defaultResponse(session);
    }

    /**
     * 返回文件上传的静态页面
     * @param session
     * @return
     */
    private Response defaultResponse(IHTTPSession session){
        return new Response(DefaultHtml.HTML_STRING);
    }

    /**
     * POST方法先进行文件存储
     * @param session
     */
    private void doPost(IHTTPSession session){
        Map<String, String> params = session.getParms();
        Map<String, String> files = new HashMap<String, String>();
        try {
            session.parseBody(files);
            System.out.println(files.size());
            for(Map.Entry<String, String> entry : files.entrySet()){
                System.out.println(entry.getKey() + ":" + entry.getValue());
                String srcFilePath = entry.getValue();
                String destFilePath = this.webRoot + "/" + params.get(entry.getKey());
                FileExtraUtils.copyFile(srcFilePath, destFilePath, false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        }
    }
}
