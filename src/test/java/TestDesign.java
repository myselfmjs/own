import com.pluto.own.Design.Strategy.ExportContextFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：pluto
 * @date ：Created in 2019/12/9 16:33
 *
 * 设计模式测试
 */
public class TestDesign {

    @Autowired
    private ExportContextFactory exportContextFactory;

    @Test
    public void  tt(){
        exportContextFactory.get("dome01").excute("dome01");

        Object o = new Object();
        exportContextFactory.get("dome02").excute("dome01");
    }
}
