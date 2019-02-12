
        package main;

import java.io.IOException;

import tools.AllInfo;
import tools.FilePathReceiver;
import upgma.FormatUPGMA;
import xlsx_stuff.CreateXlsXFile;

        public class VirulenceGenes_Main {

            public static void main(String[] args) {

                AllInfo ai = new AllInfo();
                FilePathReceiver fpr = new FilePathReceiver();

                fpr.makePath();

                ai.collectAllInfo(fpr.getPath());

                ai.getAllInfo();

                CreateXlsXFile cxf = new CreateXlsXFile();

                try {
                    cxf.collectInExcel(fpr.getPath());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                FormatUPGMA fu = new FormatUPGMA();

                fu.formatUPGMA(fpr.getPath());

            }

        }