package ekptg.faraid;

public class MasalahBerlapisTest {
	
    public static void main(String[] args) {
        String sFinal[][];
        String sFull[][];
        int iRow, iColumn;
        Faraid.Engine.Layers oLayer = new Faraid.Engine.Layers();

        try{

            oLayer.Initialize(8, 2);
            oLayer.InsertHeirDetail("ID1", "SALLEH BIN TAJUDDIN");
            oLayer.InsertHeirDetail("ID2", "SHUKRI BIN SALLEH");
            oLayer.InsertHeirDetail("ID3", "RAMLI BIN SALLEH");
            oLayer.InsertHeirDetail("ID4", "SOFEA BINTI SALLEH");
            oLayer.InsertHeirDetail("ID5", "ZAINAB BT YUSOFF");
            oLayer.InsertHeirDetail("ID6", "RASYID BIN SALLEH");
            oLayer.InsertHeirDetail("ID7", "ALIAH BIN ABDULLAH");
            oLayer.InsertHeirDetail("ID8", "Baitul Mal");

            oLayer.AssignLayerHeader("ID1", 2);
  
            oLayer.AssignLayerTashieh("ID1", 1, 15, 60);
            oLayer.AssignLayerTashieh("ID2", 1, 14, 60);
            oLayer.AssignLayerTashieh("ID3", 1, 14, 60);
            oLayer.AssignLayerTashieh("ID4", 1, 7, 60);
            oLayer.AssignLayerTashieh("ID5", 1, 10, 60);

            oLayer.AssignLayerTashieh("ID2", 2, 2, 8);
            oLayer.AssignLayerTashieh("ID3", 2, 2, 8);
            oLayer.AssignLayerTashieh("ID4", 2, 1, 8);
            oLayer.AssignLayerTashieh("ID6", 2, 2, 8);
            oLayer.AssignLayerTashieh("ID7", 2, 1, 8);
          


            if (oLayer.Validate()){
                oLayer.Calculate();

                sFinal = new String [oLayer.GetFinalResultRowSize()][oLayer.GetFinalResultColumnSize()];
                sFull = new String [oLayer.GetFullResultRowSize()][oLayer.GetFullResultColumnSize()];

                System.out.println("Final Result:");
                oLayer.GetFinalResult(sFinal);
                for (iRow = 0; iRow < sFinal.length; iRow++){
                    for (iColumn = 0; iColumn < sFinal[iRow].length; iColumn++){
                        System.out.print( sFinal[iRow][iColumn] + "\t");
                    }
                    System.out.println("");
                }
                System.out.println("");
                
                System.out.println("Full Result");
                oLayer.GetFullResult(sFull);
                for (iRow = 0; iRow < sFull.length; iRow++){
                    for (iColumn = 0; iColumn < sFull[iRow].length; iColumn++){
                        System.out.print( sFull[iRow][iColumn] + "\t");
                    }
                    System.out.println("");
                }

            }
            else{
                System.out.println("Not valid!");
            }

        }
        catch (Exception exception){
            System.err.println(exception.getMessage());
        }
    }
}

