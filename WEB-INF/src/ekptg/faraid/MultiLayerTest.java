package ekptg.faraid;

public class MultiLayerTest {
	
    public static void main(String[] args) {
        String sFinal[][];
        String sFull[][];
        int iRow, iColumn;
        Faraid.Engine.Layers oLayer = new Faraid.Engine.Layers();

        try{

            oLayer.Initialize(8, 4);
            oLayer.InsertHeirDetail("ID1", "Aisyah Alia");
            oLayer.InsertHeirDetail("ID2", "Aisyah Huda");
            oLayer.InsertHeirDetail("ID3", "Ali Alias");
            oLayer.InsertHeirDetail("ID4", "Hazlan");
            oLayer.InsertHeirDetail("ID5", "Ismail");
            oLayer.InsertHeirDetail("ID6", "Salina");
            oLayer.InsertHeirDetail("ID7", "Siti");
            oLayer.InsertHeirDetail("ID8", "Baitul Mal");

            oLayer.AssignLayerHeader("ID3", 2);
            oLayer.AssignLayerHeader("ID2", 3);
            oLayer.AssignLayerHeader("ID6", 4);

            oLayer.AssignLayerTashieh("ID1", 1, 17, 96);
            oLayer.AssignLayerTashieh("ID2", 1, 17, 96);
            oLayer.AssignLayerTashieh("ID3", 1, 34, 96);
            oLayer.AssignLayerTashieh("ID6", 1, 16, 96);
            oLayer.AssignLayerTashieh("ID7", 1, 12, 96);

            oLayer.AssignLayerTashieh("ID1", 2, 2, 6);
            oLayer.AssignLayerTashieh("ID2", 2, 2, 6);
            oLayer.AssignLayerTashieh("ID6", 2, 0, 6);
            oLayer.AssignLayerTashieh("ID7", 2, 1, 6);
            oLayer.AssignLayerTashieh("ID8", 2, 1, 6);

            oLayer.AssignLayerTashieh("ID1", 3, 0, 12);
            oLayer.AssignLayerTashieh("ID4", 3, 3, 12);
            oLayer.AssignLayerTashieh("ID5", 3, 7, 12);
            oLayer.AssignLayerTashieh("ID6", 3, 0, 12);
            oLayer.AssignLayerTashieh("ID7", 3, 2, 12);

            oLayer.AssignLayerTashieh("ID1", 4, 1, 2);
            oLayer.AssignLayerTashieh("ID8", 4, 1, 2);

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

