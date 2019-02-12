# VirulenceGenes
Grabbing, management and storing in xlsx of genes using VFDB

1. Add external libraries for readign XLSX files /not xls, if you have lower than Microsoft Office 2007/
Appache POI

2. Upload your fasta files to VFDB and COPY IN TEXT FILE like 

VFG000514  (ssaV) type III secretion system major export app...  4014   0.0   
VFG000465  (sopB/sigD) type III secretion system effector So...  3342   0.0   
VFG000495  (ssaC) type III secretion system secretin SsaC [T...  2920   0.0   
VFG000501  (sseC) type III secretion system hydrophilic tran...  2801   0.0   
VFG042066  (steC) type III secretion system effector SteC [S...  2615   0.0   
VFG000515  (ssaN) type III secretion system ATPase SsaN [TTS...  2581   0.0   
...

3. Save all Files files in folder (nothing else, just txt files)

4. Run the code, paste the folder path to the console
It will grab genes from all files, ask to name sheet name and collect in xlsx File

5. After it will requre the file name /may sound purposeless :)/
Copy the name of the file, that was just generated in the path you've past
and it will create binar sheet as well as a FORMATED UPGMA txt file
In case if you want to have cladogramm using 

http://insilico.ehu.es/dice_upgma/


  
