package ekptg.faraid;

import Faraid.Library.Constant.GenderEnum;
import Faraid.Library.Constant.RelationEnum;

public class MasalahSatuLapisTest {

    public static void main(String[] args) {
        long lBase[] = {0,0};
        long lTashieh[] = {0,0};
        Faraid.Engine.Syafie oEngine;

        try{
            oEngine = new Faraid.Engine.Syafie();

            
            System.out.println("Family #1");
            oEngine.SetGender(GenderEnum.Female);
            oEngine.SetRelationCount(RelationEnum.M, 1);
            oEngine.SetRelationCount(RelationEnum.H, 1);
            oEngine.SetRelationCount(RelationEnum.S, 2);
            oEngine.SetRelationCount(RelationEnum.S, 2);
            oEngine.SetRelationCount(RelationEnum.S, 2);
            oEngine.SetRelationCount(RelationEnum.D, 1);
            oEngine.Calculate();

            oEngine.GetRelationFraction(RelationEnum.M, lBase, lTashieh);
            System.out.println("M- " + lBase[0] + "/" + lBase[1] + " > " + lTashieh[0] + "/" + lTashieh[1]);

            oEngine.GetRelationFraction(RelationEnum.H, lBase, lTashieh);
            System.out.println("H- " + lBase[0] + "/" + lBase[1]  + " > " + lTashieh[0] + "/" + lTashieh[1]);

            oEngine.GetRelationFraction(RelationEnum.S, lBase, lTashieh);
            System.out.println("S- " + lBase[0] + "/" + lBase[1] + " > " + lTashieh[0] + "/" + lTashieh[1]);
            
            oEngine.GetRelationFraction(RelationEnum.D, lBase, lTashieh);
            System.out.println("D- " + lBase[0] + "/" + lBase[1] + " > " + lTashieh[0] + "/" + lTashieh[1]);

            oEngine.GetRelationFraction(RelationEnum.Bm, lBase, lTashieh);
            if (lTashieh[0] > 0){
                System.out.println("BM- " + lBase[0] + "/" + lBase[1]  + " > " + lTashieh[0] + "/" + lTashieh[1]);
            }
            System.out.println("\n");



            System.out.println("Family #2");
            oEngine.Initialize();
            oEngine.SetGender(GenderEnum.Male);
            oEngine.SetRelationCount(RelationEnum.W, 1);
            oEngine.SetRelationCount(RelationEnum.S, 3);
            oEngine.SetRelationCount(RelationEnum.S, 3);
            oEngine.SetRelationCount(RelationEnum.S, 3);
            oEngine.SetRelationCount(RelationEnum.D, 1);
            oEngine.SetRelationCount(RelationEnum.NotRelated, 1);
            oEngine.Calculate();

            oEngine.GetRelationFraction(RelationEnum.W, lBase, lTashieh);
            System.out.println("W- " + lBase[0] + "/" + lBase[1] + " > " + lTashieh[0] + "/" + lTashieh[1]);

            oEngine.GetRelationFraction(RelationEnum.S, lBase, lTashieh);
            System.out.println("S- " + lBase[0] + "/" + lBase[1]  + " > " + lTashieh[0] + "/" + lTashieh[1]);

            oEngine.GetRelationFraction(RelationEnum.D, lBase, lTashieh);
            System.out.println("D- " + lBase[0] + "/" + lBase[1] + " > " + lTashieh[0] + "/" + lTashieh[1]);

            oEngine.GetRelationFraction(RelationEnum.Bm, lBase, lTashieh);
            if (lTashieh[0] > 0){
                System.out.println("BM- " + lBase[0] + "/" + lBase[1]  + " > " + lTashieh[0] + "/" + lTashieh[1]);
            }
            System.out.println("\n");

            oEngine.About();
        }

        catch (Exception exception){
            System.err.println(exception.getMessage());
        }
    }
}
