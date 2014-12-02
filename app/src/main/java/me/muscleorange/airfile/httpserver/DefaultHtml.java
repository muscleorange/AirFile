package me.muscleorange.airfile.httpserver;

/**
 * Created by muscleorange on 14/12/1.
 */
public abstract class DefaultHtml {
    public static String HTML_STRING = "<html>"
            + "<head><meta charset=\"UTF-8\"><title>Air File</title></head>"
            + "<body>"
            + "<div style=\"margin:atuo;margin-top:50px;text-align:center;\">"
            + "<form action=\"\" name=\"airForm\" method=\"post\" enctype=\"multipart/form-data\">"
            + "<label><input type=\"file\" name=\"file\"/></label>"
            + "<input type=\"submit\" name=\"button\" id=\"button\" value=\"上传\" />"
            + "</form></div></body></html>";

}
