
package online.util;

import java.util.List;


public class PageUtils {
    /**
     * 每页显示条数
     */
    private static int pageSize = 10;

    /**
     * list分页取数据
     */
    public static int startIndex(int count, int currentPage) {
        int startIndex = 0;
        startIndex = pageSize * (currentPage - 1);

        return startIndex;
    }

    public static int endIndex(int count, int currentPage) {
        int endIndex = 0;
        endIndex = pageSize * (currentPage - 1) + pageSize;
        if (count < endIndex) {
            endIndex = count;
        }
        return endIndex;
    }

    //算总页数
    public static int getSumPage(List<?> list) {
        int sumPage = 0;
        if ((list.size() % pageSize == 0)) {
            sumPage = list.size() / pageSize;
        } else {
            sumPage = list.size() / pageSize + 1;
        }
        return sumPage;
    }

}
