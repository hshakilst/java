package filecrypter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipHelper {
	
	public void zipDir(String dirName, String nameZipFile) throws IOException {
        ZipOutputStream zip = null;
        FileOutputStream fW = null;
        fW = new FileOutputStream(nameZipFile);
        zip = new ZipOutputStream(fW);
        addFolderToZip("", dirName, zip);
        zip.close();
        fW.close();
    }

    private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws IOException {
        File folder = new File(srcFolder);
        if (folder.list().length == 0) {
            addFileToZip(path , srcFolder, zip, true);
        }
        else {
            for (String fileName : folder.list()) {
                if (path.equals("")) {
                    addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip, false);
                } 
                else {
                     addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip, false);
                }
            }
        }
    }

    private void addFileToZip(String path, String srcFile, ZipOutputStream zip, boolean flag) throws IOException {
        File folder = new File(srcFile);
        if (flag) {
            zip.putNextEntry(new ZipEntry(path + "/" +folder.getName() + "/"));
        }
        else {
            if (folder.isDirectory()) {
                addFolderToZip(path, srcFile, zip);
            }
            else {
            	byte[] buf = new byte[1024];
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
                while ((len = in.read(buf)) > 0) {
                    zip.write(buf, 0, len);
                }
                in.close();
            }
        }
    }
    
    private void writeFile( File file , ZipInputStream zipIn){
		try {
			OutputStream outStream = new FileOutputStream(file);
			byte[] buff = new byte[1024];
			int len;
			while ((len = zipIn.read(buff)) > 0) {
				outStream.write(buff, 0, len);
			}
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void extractZip(File srcZipfile, File destinationDir) throws IOException{
		ZipInputStream zipIn = null;
		try {
			zipIn = new ZipInputStream(new FileInputStream(srcZipfile));
			ZipEntry entry = null;
			while ((entry = zipIn.getNextEntry()) != null) {
				String outFilename = entry.getName();
				if (!new File(destinationDir, outFilename).getParentFile().exists())
					new File(destinationDir, outFilename).getParentFile().mkdirs();
				if (!entry.isDirectory())
					writeFile(new File(destinationDir, outFilename), zipIn);
			}
			System.out.println("Zip file extracted successfully...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (zipIn != null) {
				zipIn.close();
			}
		}
    }
}
