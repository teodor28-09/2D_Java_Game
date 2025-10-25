package main;

import entity.Boss;
import entity.OrcaNPC;
import object.*;

import java.util.List;
import java.util.ArrayList;

public class AssetSetter {
    main.GamePanel gp;

    public AssetSetter(main.GamePanel gp)
    {
        this.gp = gp;
    }

    public void setNPC(){
        List<int[]> path1 = List.of(
                new int[] {21, 7},
                new int[] {4, 7},
                new int[] {4, 12},
                new int[] {14, 12},
                new int[] {14, 14},
                new int[] {21, 14},
                new int[] {21, 7}
        );

        List<int[]> path2 = List.of(
                new int[] {49, 3},
                new int[] {49, 14},
                new int[] {55, 14},
                new int[] {55, 13},
                new int[] {63, 13},
                new int[] {63, 3},
                new int[] {49, 3}
        );

        List<int[]> path3 = List.of(
                new int[] {63, 13},
                new int[] {63, 3},
                new int[] {49, 3},
                new int[] {49, 14},
                new int[] {55, 14},
                new int[] {55, 13},
                new int[] {63, 13}

        );

        List<int[]> path4 = List.of(
                new int[] {101, 17},
                new int[] {101, 12},
                new int[] {91, 12},
                new int[] {91, 17},
                new int[] {101, 17}

        );

        List<int[]> path5 = List.of(
                new int[] {36, 11},
                new int[] {36, 2},
                new int[] {28, 2},
                new int[] {28, 11},
                new int[] {36, 11}

        );



        gp.npc[0] = new OrcaNPC(gp, path1);
        gp.npc[0].worldX= gp.tileSize * 21;
        gp.npc[0].worldY= gp.tileSize * 7;

        gp.npc[1] = new OrcaNPC(gp, path2);
        gp.npc[1].worldX= gp.tileSize * 49;
        gp.npc[1].worldY= gp.tileSize * 3;

        gp.npc[2] = new OrcaNPC(gp, path3);
        gp.npc[2].worldX= gp.tileSize * 63;
        gp.npc[2].worldY= gp.tileSize * 13;

        gp.npc[3] = new OrcaNPC(gp, path4);
        gp.npc[3].worldX= gp.tileSize * 101;
        gp.npc[3].worldY= gp.tileSize * 17;

        gp.npc[4] = new OrcaNPC(gp, path5);
        gp.npc[4].worldX= gp.tileSize * 36;
        gp.npc[4].worldY= gp.tileSize * 11;
    }

    public void setBoss() {
        List<int[]> dummyPath = new ArrayList<>(); // nu e folosită dacă folosești followPlayer()

        Boss boss = new Boss(gp, dummyPath);
        boss.worldX = 83 * gp.tileSize;
        boss.worldY = 11 * gp.tileSize;

        gp.npc[6] = boss; // sau gp.enemy[0], în funcție de sistemul tău
    }

    public void setObject(){
        //level 1
        gp.obj[0] = new ObjectKey();
        gp.obj[0].worldX = gp.tileSize * 1;
        gp.obj[0].worldY = gp.tileSize * 1;

        gp.obj[1] = new ObjectKey();
        gp.obj[1].worldX = gp.tileSize * 30;
        gp.obj[1].worldY = gp.tileSize * 5;

        //level 2
        gp.obj[2] = new ObjectKey();
        gp.obj[2].worldX = gp.tileSize * 44;
        gp.obj[2].worldY = gp.tileSize * 5;

        gp.obj[3] = new ObjectKey();
        gp.obj[3].worldX = gp.tileSize * 78;
        gp.obj[3].worldY = gp.tileSize * 1;

        //level 3
        gp.obj[4] = new ObjectKey();
        gp.obj[4].worldX = gp.tileSize * 84;
        gp.obj[4].worldY = gp.tileSize * 1;

        gp.obj[5] = new ObjectKey();
        gp.obj[5].worldX = gp.tileSize * 89;
        gp.obj[5].worldY = gp.tileSize * 15;

        //level 1
        gp.obj[6] = new ObjectCoins();
        gp.obj[6].worldX = gp.tileSize * 1;
        gp.obj[6].worldY = gp.tileSize * 5;

        gp.obj[7] = new ObjectCoins();
        gp.obj[7].worldX = gp.tileSize * 9;
        gp.obj[7].worldY = gp.tileSize * 1;

        gp.obj[8] = new ObjectCoins();
        gp.obj[8].worldX = gp.tileSize * 9;
        gp.obj[8].worldY = gp.tileSize * 14;

        gp.obj[9] = new ObjectCoins();
        gp.obj[9].worldX = gp.tileSize * 33;
        gp.obj[9].worldY = gp.tileSize * 15;

        //level 2
        gp.obj[10] = new ObjectCoins();
        gp.obj[10].worldX = gp.tileSize * 50;
        gp.obj[10].worldY = gp.tileSize * 18;

        gp.obj[11] = new ObjectCoins();
        gp.obj[11].worldX = gp.tileSize * 78;
        gp.obj[11].worldY = gp.tileSize * 4;

        gp.obj[12] = new ObjectCoins();
        gp.obj[12].worldX = gp.tileSize * 78;
        gp.obj[12].worldY = gp.tileSize * 15;

        //level 3
        gp.obj[13] = new ObjectCoins();
        gp.obj[13].worldX = gp.tileSize * 80;
        gp.obj[13].worldY = gp.tileSize * 1;

        gp.obj[14] = new ObjectCoins();
        gp.obj[14].worldX = gp.tileSize * 81;
        gp.obj[14].worldY = gp.tileSize * 18;

        gp.obj[15] = new ObjectCoins();
        gp.obj[15].worldX = gp.tileSize * 82;
        gp.obj[15].worldY = gp.tileSize * 18;

        gp.obj[16] = new ObjectCoins();
        gp.obj[16].worldX = gp.tileSize * 104;
        gp.obj[16].worldY = gp.tileSize * 12;

        gp.obj[17] = new ObjectCoins();
        gp.obj[17].worldX = gp.tileSize * 90;
        gp.obj[17].worldY = gp.tileSize * 18;

        gp.obj[18] = new ObjectCoins();
        gp.obj[18].worldX = gp.tileSize * 118;
        gp.obj[18].worldY = gp.tileSize * 4;

        gp.obj[19] = new ObjectCoins();
        gp.obj[19].worldX = gp.tileSize * 118;
        gp.obj[19].worldY = gp.tileSize * 16;

        //level 1
        gp.obj[20] = new ObjectChest(gp);
        gp.obj[20].worldX = gp.tileSize * 3;
        gp.obj[20].worldY = gp.tileSize * 16;

        //level 2
        gp.obj[21] = new ObjectChest(gp);
        gp.obj[21].worldX = gp.tileSize * 74;
        gp.obj[21].worldY = gp.tileSize * 18;

        //level 3
        gp.obj[22] = new ObjectChest(gp);
        gp.obj[22].worldX = gp.tileSize * 80;
        gp.obj[22].worldY = gp.tileSize * 3;

        gp.obj[23] = new ObjectChest(gp);
        gp.obj[23].worldX = gp.tileSize * 115;
        gp.obj[23].worldY = gp.tileSize * 4;

        //usa level 1
        gp.obj[24] = new ObjectDoorLevel();
        gp.obj[24].worldX = gp.tileSize * 39;
        gp.obj[24].worldY = gp.tileSize * 10;

        //usa level 2
        gp.obj[25] = new ObjectDoorLevel();
        gp.obj[25].worldX = gp.tileSize * 79;
        gp.obj[25].worldY = gp.tileSize * 10;

        //usa level 3
        gp.obj[26] = new ObjectDoorLevel();
        gp.obj[26].worldX = gp.tileSize * 119;
        gp.obj[26].worldY = gp.tileSize * 9;

        //maneta
        gp.obj[200] = new ObjectLever(gp);
        gp.obj[200].worldX = gp.tileSize * 118;
        gp.obj[200].worldY = gp.tileSize ;

        //gratii

        gp.obj[201] = new ObjectGratii();
        gp.obj[201].worldX = 118 * gp.tileSize;
        gp.obj[201].worldY = 8 * gp.tileSize;

        gp.obj[202] = new ObjectGratii();
        gp.obj[202].worldX = 117 * gp.tileSize;
        gp.obj[202].worldY = 9 * gp.tileSize;

        gp.obj[203] = new ObjectGratii();
        gp.obj[203].worldX = 118 * gp.tileSize;
        gp.obj[203].worldY = 10 * gp.tileSize;

        gp.obj[204] = new ObjectGratii();
        gp.obj[204].worldX = 117 * gp.tileSize;
        gp.obj[204].worldY = 8 * gp.tileSize;

        gp.obj[205] = new ObjectGratii();
        gp.obj[205].worldX = 117 * gp.tileSize;
        gp.obj[205].worldY = 10 * gp.tileSize;

        // ------------------------------------------------- TEPI --------------------------------------------------------
//        gp.obj[27] = new ObjectSpike(gp, 1);
//        gp.obj[27].worldX = gp.tileSize * 3;
//        gp.obj[27].worldY = gp.tileSize * 3;

        gp.obj[28] = new ObjectSpike(gp, 1);
        gp.obj[28].worldX = gp.tileSize * 44;
        gp.obj[28].worldY = gp.tileSize * 6;

        gp.obj[29] = new ObjectSpike(gp, 1);
        gp.obj[29].worldX = gp.tileSize * 44;
        gp.obj[29].worldY = gp.tileSize * 7;

        gp.obj[30] = new ObjectSpike(gp, 1);
        gp.obj[30].worldX = gp.tileSize * 45;
        gp.obj[30].worldY = gp.tileSize * 5;

        gp.obj[31] = new ObjectSpike(gp, 1);
        gp.obj[31].worldX = gp.tileSize * 46;
        gp.obj[31].worldY = gp.tileSize * 5;

        gp.obj[32] = new ObjectSpike(gp, 1);
        gp.obj[32].worldX = gp.tileSize * 49;
        gp.obj[32].worldY = gp.tileSize * 18;

        gp.obj[33] = new ObjectSpike(gp, 1);
        gp.obj[33].worldX = gp.tileSize * 49;
        gp.obj[33].worldY = gp.tileSize * 17;

        gp.obj[34] = new ObjectSpike(gp, 1);
        gp.obj[34].worldX = gp.tileSize * 50;
        gp.obj[34].worldY = gp.tileSize * 17;

        gp.obj[35] = new ObjectSpike(gp, 1);
        gp.obj[35].worldX = gp.tileSize * 68;
        gp.obj[35].worldY = gp.tileSize * 17;

        gp.obj[36] = new ObjectSpike(gp, 1);
        gp.obj[36].worldX = gp.tileSize * 68;
        gp.obj[36].worldY = gp.tileSize * 18;

        gp.obj[37] = new ObjectSpike(gp, 1);
        gp.obj[37].worldX = gp.tileSize * 69;
        gp.obj[37].worldY = gp.tileSize * 17;

        gp.obj[38] = new ObjectSpike(gp, 1);
        gp.obj[38].worldX = gp.tileSize * 69;
        gp.obj[38].worldY = gp.tileSize * 18;

        gp.obj[39] = new ObjectSpike(gp, 1);
        gp.obj[39].worldX = gp.tileSize * 78;
        gp.obj[39].worldY = gp.tileSize * 9;

        gp.obj[40] = new ObjectSpike(gp, 1);
        gp.obj[40].worldX = gp.tileSize * 77;
        gp.obj[40].worldY = gp.tileSize * 10;

        gp.obj[41] = new ObjectSpike(gp, 1);
        gp.obj[41].worldX = gp.tileSize * 78;
        gp.obj[41].worldY = gp.tileSize * 11;

        gp.obj[42] = new ObjectSpike(gp, 1);
        gp.obj[42].worldX = gp.tileSize * 70;
        gp.obj[42].worldY = gp.tileSize * 3;

        gp.obj[43] = new ObjectSpike(gp, 1);
        gp.obj[43].worldX = gp.tileSize * 72;
        gp.obj[43].worldY = gp.tileSize * 3;

        gp.obj[44] = new ObjectSpike(gp, 1);
        gp.obj[44].worldX = gp.tileSize * 70;
        gp.obj[44].worldY = gp.tileSize * 17;

        gp.obj[45] = new ObjectSpike(gp, 1);
        gp.obj[45].worldX = gp.tileSize * 70;
        gp.obj[45].worldY = gp.tileSize * 18;

        gp.obj[46] = new ObjectSpike(gp, 1);
        gp.obj[46].worldX = gp.tileSize * 75;
        gp.obj[46].worldY = gp.tileSize * 17;

        gp.obj[47] = new ObjectSpike(gp, 1);
        gp.obj[47].worldX = gp.tileSize * 75;
        gp.obj[47].worldY = gp.tileSize * 15;

        gp.obj[48] = new ObjectSpike(gp, 1);
        gp.obj[48].worldX = gp.tileSize * 76;
        gp.obj[48].worldY = gp.tileSize * 14;

        gp.obj[49] = new ObjectSpike(gp, 1);
        gp.obj[49].worldX = gp.tileSize * 77;
        gp.obj[49].worldY = gp.tileSize * 15;

        gp.obj[50] = new ObjectSpike(gp, 1);
        gp.obj[50].worldX = gp.tileSize * 77;
        gp.obj[50].worldY = gp.tileSize * 17;

        gp.obj[51] = new ObjectSpike(gp, 1);
        gp.obj[51].worldX = gp.tileSize * 76;
        gp.obj[51].worldY = gp.tileSize * 18;

        gp.obj[52] = new ObjectSpike(gp, 1);
        gp.obj[52].worldX = gp.tileSize * 76;
        gp.obj[52].worldY = gp.tileSize * 16;

        gp.obj[53] = new ObjectSpike(gp, 1);
        gp.obj[53].worldX = gp.tileSize * 77;
        gp.obj[53].worldY = gp.tileSize * 9;

        gp.obj[54] = new ObjectSpike(gp, 1);
        gp.obj[54].worldX = gp.tileSize * 77;
        gp.obj[54].worldY = gp.tileSize * 11;

        gp.obj[55] = new ObjectSpike(gp, 1);
        gp.obj[55].worldX = gp.tileSize * 80;
        gp.obj[55].worldY = gp.tileSize * 9;

        gp.obj[56] = new ObjectSpike(gp, 1);
        gp.obj[56].worldX = gp.tileSize * 81;
        gp.obj[56].worldY = gp.tileSize * 9;

        gp.obj[57] = new ObjectSpike(gp, 1);
        gp.obj[57].worldX = gp.tileSize * 81;
        gp.obj[57].worldY = gp.tileSize * 10;

        gp.obj[58] = new ObjectSpike(gp, 1);
        gp.obj[58].worldX = gp.tileSize * 81;
        gp.obj[58].worldY = gp.tileSize * 11;

        gp.obj[59] = new ObjectSpike(gp, 1);
        gp.obj[59].worldX = gp.tileSize * 80;
        gp.obj[59].worldY = gp.tileSize * 11;

        gp.obj[60] = new ObjectSpike(gp, 1);
        gp.obj[60].worldX = gp.tileSize * 80;
        gp.obj[60].worldY = gp.tileSize * 16;

        gp.obj[61] = new ObjectSpike(gp, 1);
        gp.obj[61].worldX = gp.tileSize * 81;
        gp.obj[61].worldY = gp.tileSize * 16;

        gp.obj[62] = new ObjectSpike(gp, 1);
        gp.obj[62].worldX = gp.tileSize * 82;
        gp.obj[62].worldY = gp.tileSize * 16;

        gp.obj[63] = new ObjectSpike(gp, 1);
        gp.obj[63].worldX = gp.tileSize * 80;
        gp.obj[63].worldY = gp.tileSize * 17;

        gp.obj[64] = new ObjectSpike(gp, 1);
        gp.obj[64].worldX = gp.tileSize * 81;
        gp.obj[64].worldY = gp.tileSize * 17;

        gp.obj[65] = new ObjectSpike(gp, 1);
        gp.obj[65].worldX = gp.tileSize * 82;
        gp.obj[65].worldY = gp.tileSize * 17;

        gp.obj[66] = new ObjectSpike(gp, 1);
        gp.obj[66].worldX = gp.tileSize * 82;
        gp.obj[66].worldY = gp.tileSize * 2;

        gp.obj[67] = new ObjectSpike(gp, 1);
        gp.obj[67].worldX = gp.tileSize * 82;
        gp.obj[67].worldY = gp.tileSize * 3;

        gp.obj[68] = new ObjectSpike(gp, 1);
        gp.obj[68].worldX = gp.tileSize * 83;
        gp.obj[68].worldY = gp.tileSize * 2;

        gp.obj[69] = new ObjectSpike(gp, 1);
        gp.obj[69].worldX = gp.tileSize * 83;
        gp.obj[69].worldY = gp.tileSize * 3;

        gp.obj[70] = new ObjectSpike(gp, 1);
        gp.obj[70].worldX = gp.tileSize * 84;
        gp.obj[70].worldY = gp.tileSize * 2;

        gp.obj[71] = new ObjectSpike(gp, 1);
        gp.obj[71].worldX = gp.tileSize * 84;
        gp.obj[71].worldY = gp.tileSize * 3;

        gp.obj[72] = new ObjectSpike(gp, 1);
        gp.obj[72].worldX = gp.tileSize * 93;
        gp.obj[72].worldY = gp.tileSize * 18;

        gp.obj[73] = new ObjectSpike(gp, 1);
        gp.obj[73].worldX = gp.tileSize * 93;
        gp.obj[73].worldY = gp.tileSize * 17;

        gp.obj[74] = new ObjectSpike(gp, 1);
        gp.obj[74].worldX = gp.tileSize * 93;
        gp.obj[74].worldY = gp.tileSize * 16;

        gp.obj[75] = new ObjectSpike(gp, 1);
        gp.obj[75].worldX = gp.tileSize * 92;
        gp.obj[75].worldY = gp.tileSize * 18;

        gp.obj[76] = new ObjectSpike(gp, 1);
        gp.obj[76].worldX = gp.tileSize * 92;
        gp.obj[76].worldY = gp.tileSize * 17;

        gp.obj[77] = new ObjectSpike(gp, 1);
        gp.obj[77].worldX = gp.tileSize * 92;
        gp.obj[77].worldY = gp.tileSize * 16;

        gp.obj[78] = new ObjectSpike(gp, 1);
        gp.obj[78].worldX = gp.tileSize * 91;
        gp.obj[78].worldY = gp.tileSize * 18;

        gp.obj[79] = new ObjectSpike(gp, 1);
        gp.obj[79].worldX = gp.tileSize * 91;
        gp.obj[79].worldY = gp.tileSize * 17;

        gp.obj[80] = new ObjectSpike(gp, 1);
        gp.obj[80].worldX = gp.tileSize * 91;
        gp.obj[80].worldY = gp.tileSize * 16;

        gp.obj[81] = new ObjectSpike(gp, 1);
        gp.obj[81].worldX = gp.tileSize * 90;
        gp.obj[81].worldY = gp.tileSize * 17;

        gp.obj[82] = new ObjectSpike(gp, 1);
        gp.obj[82].worldX = gp.tileSize * 90;
        gp.obj[82].worldY = gp.tileSize * 16;

        gp.obj[83] = new ObjectSpike(gp, 1);
        gp.obj[83].worldX = gp.tileSize * 89;
        gp.obj[83].worldY = gp.tileSize * 17;

        gp.obj[84] = new ObjectSpike(gp, 1);
        gp.obj[84].worldX = gp.tileSize * 89;
        gp.obj[84].worldY = gp.tileSize * 16;

        gp.obj[85] = new ObjectSpike(gp, 1);
        gp.obj[85].worldX = gp.tileSize * 93;
        gp.obj[85].worldY = gp.tileSize * 14;

        gp.obj[86] = new ObjectSpike(gp, 1);
        gp.obj[86].worldX = gp.tileSize * 93;
        gp.obj[86].worldY = gp.tileSize * 13;

        gp.obj[87] = new ObjectSpike(gp, 1);
        gp.obj[87].worldX = gp.tileSize * 93;
        gp.obj[87].worldY = gp.tileSize * 12;

        gp.obj[88] = new ObjectSpike(gp, 1);
        gp.obj[88].worldX = gp.tileSize * 92;
        gp.obj[88].worldY = gp.tileSize * 14;

        gp.obj[89] = new ObjectSpike(gp, 1);
        gp.obj[89].worldX = gp.tileSize * 92;
        gp.obj[89].worldY = gp.tileSize * 13;

        gp.obj[90] = new ObjectSpike(gp, 1);
        gp.obj[90].worldX = gp.tileSize * 92;
        gp.obj[90].worldY = gp.tileSize * 12;

        gp.obj[91] = new ObjectSpike(gp, 1);
        gp.obj[91].worldX = gp.tileSize * 91;
        gp.obj[91].worldY = gp.tileSize * 14;

        gp.obj[92] = new ObjectSpike(gp, 1);
        gp.obj[92].worldX = gp.tileSize * 91;
        gp.obj[92].worldY = gp.tileSize * 13;

        gp.obj[93] = new ObjectSpike(gp, 1);
        gp.obj[93].worldX = gp.tileSize * 91;
        gp.obj[93].worldY = gp.tileSize * 12;

        gp.obj[94] = new ObjectSpike(gp, 1);
        gp.obj[94].worldX = gp.tileSize * 90;
        gp.obj[94].worldY = gp.tileSize * 14;

        gp.obj[95] = new ObjectSpike(gp, 1);
        gp.obj[95].worldX = gp.tileSize * 90;
        gp.obj[95].worldY = gp.tileSize * 13;

        gp.obj[96] = new ObjectSpike(gp, 1);
        gp.obj[96].worldX = gp.tileSize * 90;
        gp.obj[96].worldY = gp.tileSize * 12;

        gp.obj[97] = new ObjectSpike(gp, 1);
        gp.obj[97].worldX = gp.tileSize * 89;
        gp.obj[97].worldY = gp.tileSize * 14;

        gp.obj[98] = new ObjectSpike(gp, 1);
        gp.obj[98].worldX = gp.tileSize * 89;
        gp.obj[98].worldY = gp.tileSize * 13;

        gp.obj[99] = new ObjectSpike(gp, 1);
        gp.obj[99].worldX = gp.tileSize * 99;
        gp.obj[99].worldY = gp.tileSize * 18;

        gp.obj[100] = new ObjectSpike(gp, 1);
        gp.obj[100].worldX = gp.tileSize * 99;
        gp.obj[100].worldY = gp.tileSize * 17;

        gp.obj[101] = new ObjectSpike(gp, 1);
        gp.obj[101].worldX = gp.tileSize * 100;
        gp.obj[101].worldY = gp.tileSize * 18;

        gp.obj[102] = new ObjectSpike(gp, 1);
        gp.obj[102].worldX = gp.tileSize * 100;
        gp.obj[102].worldY = gp.tileSize * 17;

        gp.obj[103] = new ObjectSpike(gp, 1);
        gp.obj[103].worldX = gp.tileSize * 100;
        gp.obj[103].worldY = gp.tileSize * 16;

        gp.obj[104] = new ObjectSpike(gp, 1);
        gp.obj[104].worldX = gp.tileSize * 101;
        gp.obj[104].worldY = gp.tileSize * 18;

        gp.obj[105] = new ObjectSpike(gp, 1);
        gp.obj[105].worldX = gp.tileSize * 101;
        gp.obj[105].worldY = gp.tileSize * 17;

        gp.obj[106] = new ObjectSpike(gp, 1);
        gp.obj[106].worldX = gp.tileSize * 101;
        gp.obj[106].worldY = gp.tileSize * 16;

        gp.obj[107] = new ObjectSpike(gp, 1);
        gp.obj[107].worldX = gp.tileSize * 102;
        gp.obj[107].worldY = gp.tileSize * 18;

        gp.obj[108] = new ObjectSpike(gp, 1);
        gp.obj[108].worldX = gp.tileSize * 102;
        gp.obj[108].worldY = gp.tileSize * 17;

        gp.obj[109] = new ObjectSpike(gp, 1);
        gp.obj[109].worldX = gp.tileSize * 102;
        gp.obj[109].worldY = gp.tileSize * 16;

        gp.obj[110] = new ObjectSpike(gp, 1);
        gp.obj[110].worldX = gp.tileSize * 103;
        gp.obj[110].worldY = gp.tileSize * 18;

        gp.obj[111] = new ObjectSpike(gp, 1);
        gp.obj[111].worldX = gp.tileSize * 103;
        gp.obj[111].worldY = gp.tileSize * 17;

        gp.obj[112] = new ObjectSpike(gp, 1);
        gp.obj[112].worldX = gp.tileSize * 103;
        gp.obj[112].worldY = gp.tileSize * 16;

        gp.obj[113] = new ObjectSpike(gp, 1);
        gp.obj[113].worldX = gp.tileSize * 103;
        gp.obj[113].worldY = gp.tileSize * 12;

        gp.obj[114] = new ObjectSpike(gp, 1);
        gp.obj[114].worldX = gp.tileSize * 103;
        gp.obj[114].worldY = gp.tileSize * 13;

        gp.obj[115] = new ObjectSpike(gp, 1);
        gp.obj[115].worldX = gp.tileSize * 103;
        gp.obj[115].worldY = gp.tileSize * 14;

        gp.obj[116] = new ObjectSpike(gp, 1);
        gp.obj[116].worldX = gp.tileSize * 104;
        gp.obj[116].worldY = gp.tileSize * 13;

        gp.obj[117] = new ObjectSpike(gp, 1);
        gp.obj[117].worldX = gp.tileSize * 104;
        gp.obj[117].worldY = gp.tileSize * 14;

        gp.obj[118] = new ObjectSpike(gp, 1);
        gp.obj[118].worldX = gp.tileSize * 102;
        gp.obj[118].worldY = gp.tileSize * 12;

        gp.obj[119] = new ObjectSpike(gp, 1);
        gp.obj[119].worldX = gp.tileSize * 102;
        gp.obj[119].worldY = gp.tileSize * 13;

        gp.obj[120] = new ObjectSpike(gp, 1);
        gp.obj[120].worldX = gp.tileSize * 101;
        gp.obj[120].worldY = gp.tileSize * 12;

        gp.obj[121] = new ObjectSpike(gp, 1);
        gp.obj[121].worldX = gp.tileSize * 101;
        gp.obj[121].worldY = gp.tileSize * 13;

        gp.obj[122] = new ObjectSpike(gp, 1);
        gp.obj[122].worldX = gp.tileSize * 101;
        gp.obj[122].worldY = gp.tileSize * 14;

        gp.obj[123] = new ObjectSpike(gp, 1);
        gp.obj[123].worldX = gp.tileSize * 100;
        gp.obj[123].worldY = gp.tileSize * 12;

        gp.obj[124] = new ObjectSpike(gp, 1);
        gp.obj[124].worldX = gp.tileSize * 100;
        gp.obj[124].worldY = gp.tileSize * 13;

        gp.obj[125] = new ObjectSpike(gp, 1);
        gp.obj[125].worldX = gp.tileSize * 100;
        gp.obj[125].worldY = gp.tileSize * 14;

        gp.obj[126] = new ObjectSpike(gp, 1);
        gp.obj[126].worldX = gp.tileSize * 99;
        gp.obj[126].worldY = gp.tileSize * 12;

        gp.obj[127] = new ObjectSpike(gp, 1);
        gp.obj[127].worldX = gp.tileSize * 99;
        gp.obj[127].worldY = gp.tileSize * 13;

        gp.obj[128] = new ObjectSpike(gp, 1);
        gp.obj[128].worldX = gp.tileSize * 99;
        gp.obj[128].worldY = gp.tileSize * 14;

        gp.obj[129] = new ObjectSpike(gp, 1);
        gp.obj[129].worldX = gp.tileSize * 117;
        gp.obj[129].worldY = gp.tileSize * 16;

        gp.obj[130] = new ObjectSpike(gp, 1);
        gp.obj[130].worldX = gp.tileSize * 117;
        gp.obj[130].worldY = gp.tileSize * 15;

        gp.obj[131] = new ObjectSpike(gp, 1);
        gp.obj[131].worldX = gp.tileSize * 118;
        gp.obj[131].worldY = gp.tileSize * 15;

        gp.obj[132] = new ObjectSpike(gp, 1);
        gp.obj[132].worldX = gp.tileSize * 112;
        gp.obj[132].worldY = gp.tileSize * 2;

        gp.obj[133] = new ObjectSpike(gp, 1);
        gp.obj[133].worldX = gp.tileSize * 112;
        gp.obj[133].worldY = gp.tileSize * 3;

        gp.obj[134] = new ObjectSpike(gp, 1);
        gp.obj[134].worldX = gp.tileSize * 110;
        gp.obj[134].worldY = gp.tileSize;

        gp.obj[135] = new ObjectSpike(gp, 1);
        gp.obj[135].worldX = gp.tileSize * 110;
        gp.obj[135].worldY = gp.tileSize * 3;

        gp.obj[136] = new ObjectSpike(gp, 1);
        gp.obj[136].worldX = gp.tileSize * 110;
        gp.obj[136].worldY = gp.tileSize * 2;

        gp.obj[137] = new ObjectSpike(gp, 1);
        gp.obj[137].worldX = gp.tileSize * 116;
        gp.obj[137].worldY = gp.tileSize;

        gp.obj[138] = new ObjectSpike(gp, 1);
        gp.obj[138].worldX = gp.tileSize * 116;
        gp.obj[138].worldY = gp.tileSize * 2;

        gp.obj[139] = new ObjectSpike(gp, 1);
        gp.obj[139].worldX = gp.tileSize * 116;
        gp.obj[139].worldY = gp.tileSize * 3;

        gp.obj[140] = new ObjectSpike(gp, 1);
        gp.obj[140].worldX = gp.tileSize * 116;
        gp.obj[140].worldY = gp.tileSize * 4;

        gp.obj[141] = new ObjectSpike(gp, 1);
        gp.obj[141].worldX = gp.tileSize * 117;
        gp.obj[141].worldY = gp.tileSize * 4;

        gp.obj[142] = new ObjectSpike(gp, 1);
        gp.obj[142].worldX = gp.tileSize * 117;
        gp.obj[142].worldY = gp.tileSize * 3;

        gp.obj[143] = new ObjectSpike(gp, 1);
        gp.obj[143].worldX = gp.tileSize * 117;
        gp.obj[143].worldY = gp.tileSize * 2;

        gp.obj[144] = new ObjectSpike(gp, 1);
        gp.obj[144].worldX = gp.tileSize * 117;
        gp.obj[144].worldY = gp.tileSize * 1;

        gp.obj[145] = new ObjectSpike(gp, 1);
        gp.obj[145].worldX = gp.tileSize * 110;
        gp.obj[145].worldY = gp.tileSize * 4;


}
}
