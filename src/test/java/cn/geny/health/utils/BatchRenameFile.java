package cn.geny.health.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/4/16 20:56
 */
public class BatchRenameFile {

    @Test
    public void testRename(){
        String directory = "F:\\Downloads\\tmp1";
        String toDirectory = "F:\\Downloads\\tmp\\";
        File files = new File(directory);
        Arrays.stream(files.listFiles()).forEach(file-> file.renameTo(new File(toDirectory+file.getName().split("\\.")[0])));
    }
}
