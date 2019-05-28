
package online.entity;

import java.util.List;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 23:31
 * @Version V2.0
 */
public class PageUtils {

    private static int pageSize = 10;//每页显示条数

    //list分页取数据
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
    public static <E> int getSumPage(List<E> list) {
        int sumPage = 0;
        if (list.size() % pageSize == 0)
            sumPage = list.size() / pageSize;
        else
            sumPage = list.size() / pageSize + 1;
        return sumPage;
    }

}
