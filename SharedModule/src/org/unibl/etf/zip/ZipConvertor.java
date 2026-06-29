package org.unibl.etf.zip;

import java.io.*;
import java.util.zip.*;

public class ZipConvertor {

    public static String convertToZip(String filePath) throws IOException {
        File file = new File(filePath);
        String zipFilePath = file.getParent() + File.separator + file.getName().replace(".pdf", ".zip");

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            // adding PDF to ZIP
            try (FileInputStream fis = new FileInputStream(file)) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);

                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
            }
        }
        return zipFilePath;
    }
}
