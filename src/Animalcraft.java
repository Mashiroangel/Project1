import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Animalcraft {
    //记忆地图
    private static char[][] copyArray(char[][] array) {
        char[][] newArray = new char[7][9];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    //打印地图
    public static void print(char[][] animalmap, char[][] undermap) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (animalmap[i][j] == '0') {
                    switch (undermap[i][j]) {
                        case '0':
                            System.out.print(" 　 ");
                            break;
                        case '1':
                            System.out.print(" 水 ");
                            break;
                        case '2':
                            System.out.print(" 陷 ");
                            break;
                        case '3':
                            System.out.print(" 家 ");
                            break;
                        case '4':
                            System.out.print(" 陷 ");
                            break;
                        case '5':
                            System.out.print(" 家 ");
                            break;
                        default:
                            System.out.println();
                    }
                } else {
                    switch (animalmap[i][j]) {
                        case '0':
                            System.out.print(" 　 ");
                            break;
                        case '1':
                            System.out.print(" 鼠1");
                            break;
                        case '2':
                            System.out.print(" 猫2");
                            break;
                        case '3':
                            System.out.print(" 狼3");
                            break;
                        case '4':
                            System.out.print(" 狗4");
                            break;
                        case '5':
                            System.out.print(" 豹5");
                            break;
                        case '6':
                            System.out.print(" 虎6");
                            break;
                        case '7':
                            System.out.print(" 狮7");
                            break;
                        case '8':
                            System.out.print(" 象8");
                            break;
                        case 'a':
                            System.out.print("1鼠 ");
                            break;
                        case 'b':
                            System.out.print("2猫 ");
                            break;
                        case 'c':
                            System.out.print("3狼 ");
                            break;
                        case 'd':
                            System.out.print("4狗 ");
                            break;
                        case 'e':
                            System.out.print("5豹 ");
                            break;
                        case 'f':
                            System.out.print("6虎 ");
                            break;
                        case 'g':
                            System.out.print("7狮 ");
                            break;
                        case 'h':
                            System.out.print("8象 ");
                            break;
                        default:
                            System.out.println();
                    }
                }
            }
            System.out.println();
        }
    }

    //打印帮助
    public static int help(String step, char[][] animalmap, char[][] undermap, char[][][] memory) {
        if (step.equals("help")) {
            System.out.println("指令介绍:\n" +
                    "\n" +
                    "1. 移动指令\n" +
                    "        移动指令由两个部分组成。\n" +
                    "        第一个部分是数字1-8,根据战斗力分别对应鼠(1),猫(2),狼(3),狗(4),豹(5),虎(6),狮(7),象(8)\n" +
                    "        第二个部分是字母wasd中的一个,w对应上方向,a对应左方向,s对应下方向,d对应右方向\n" +
                    "        比如指令 \"1d\" 表示鼠向右走, \"4w\" 表示狗向左走\n" +
                    "\n" +
                    "2. 游戏指令\n" +
                    "        输入 restart 重新开始游戏\n" +
                    "        输入 help 查看帮助\n" +
                    "        输入 undo 悔棋\n" +
                    "        输入 redo 取消悔棋\n" +
                    "        输入 exit 退出游戏");
            return 0;
        }
        if (step.equals("exit"))
            System.exit(0);
        return 1;
    }

    //寻找动物位置
    public static int[] findr(String a, char[][] b) {
        String m = null;
        int[] e = {9, 9};
        switch (a) {
            case ("1"):
                m = "1";
                break;
            case ("2"):
                m = "2";
                break;
            case ("3"):
                m = "3";
                break;
            case ("4"):
                m = "4";
                break;
            case ("5"):
                m = "5";
                break;
            case ("6"):
                m = "6";
                break;
            case ("7"):
                m = "7";
                break;
            case ("8"):
                m = "8";
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                String position = "" + b[i][j];
                if (position.equals(m)) {
                    e[0] = i;
                    e[1] = j;
                    return e;
                }
            }
        }
        System.out.println("对不起，未曾找到你指名的动物，是不是死了?");

        return e;
    }

    public static int[] findl(String a, char[][] b) {
        String m = null;
        int[] e = {9, 9};
        switch (a) {
            case ("1"):
                m = "a";
                break;
            case ("2"):
                m = "b";
                break;
            case ("3"):
                m = "c";
                break;
            case ("4"):
                m = "d";
                break;
            case ("5"):
                m = "e";
                break;
            case ("6"):
                m = "f";
                break;
            case ("7"):
                m = "g";
                break;
            case ("8"):
                m = "h";
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                String position = "" + b[i][j];
                if (position.equals(m)) {
                    e[0] = i;
                    e[1] = j;
                    return e;
                }
            }
        }
        System.out.println("对不起，未曾找到你指名的动物，是不是死了?");
        return e;
    }

    //指令判断
    public static int judgecommand(String command, String ani, String move) {
        int aniint = Integer.parseInt(ani);
        if (aniint > 8 || aniint < 0) {
            System.out.println("动物只有1~8呦");
            return 0;
        } else if (move.equals("w") || move.equals("a") || move.equals("s") || move.equals("d")) {
            return 1;
        }
        System.out.println("没有这个方向");
        return 0;
    }

    //战斗力比较
    public static int comparestrength(boolean player, char a, char b, int x, int y, char[][] undermap) {
        int aint = a;
        int bint = b;
        int t = Math.max(aint, bint) - Math.min(aint, bint);
        if (aint > 60) {
            aint = aint - 48;
        }
        if (bint > 60) {
            bint = bint - 48;

        }
        char map = undermap[x][y];
        if ((map == '2' && player) || (map == '4' && player == false)) {
            return 1;
        }
        if (t < 10 && a != '0' && b != '0') {
            System.out.println("同伙之间不能互相残杀");
            return 0;
        }
        if (undermap[x][y] != '2' && undermap[x][y] != '4' && aint < bint && (bint - aint) != 7) {
            System.out.println("不能自杀呀");
            return 0;
        }
        if (aint - bint == 7 && a != '0' && b != '0') {
            System.out.println("不能自杀呀");
            return 0;
        }

        if (undermap[x][y] == '2' && !player) {
            if (aint < bint && (bint - aint) != 8) {
                System.out.println("不能自杀呀");
                return 0;
            }
        }
        if (undermap[x][y] == '4' && player) {
            if (aint < bint && (bint - aint) != 8) {
                System.out.println("不能自杀呀");
                return 0;
            }
        }

        return 1;

    }

    //寻常移动判断
    public static int[] normalmove(String ani, String move, int x, int y, char[][] animals, char[][] undermap, boolean player)  {
        int[] temp = new int[2];
        temp[0] = 9;
        temp[1] = 9;
        char tempmap = undermap[x][y];
        switch (move) {
            case ("w"):
                x = x - 1;
                if (x < 0) {
                    System.out.println("动物不能走出界");
                    return temp;
                }
                if (0 == comparestrength(player, animals[x + 1][y], animals[x][y], x, y, undermap)) {
                    return temp;
                }
                break;
            case ("a"):
                y = y - 1;
                if (y < 0) {
                    System.out.println("动物不能走出界");
                    return temp;
                }
                if (0 == comparestrength(player, animals[x][y + 1], animals[x][y], x, y, undermap)) {
                    return temp;
                }
                break;
            case ("s"):
                x = x + 1;
                if (x > 6) {
                    System.out.println("动物不能走出界");
                    return temp;
                }
                if (0 == comparestrength(player, animals[x - 1][y], animals[x][y], x, y, undermap)) {
                    return temp;
                }
                break;
            case ("d"):
                y = y + 1;
                if (y > 8) {
                    System.out.println("动物不能走出界");
                    return temp;
                }
                if (0 == comparestrength(player, animals[x][y - 1], animals[x][y], x, y, undermap)) {
                    return temp;
                }
        }
        if (specialjudge(ani, x, y, animals, tempmap) == 0) return temp;
        if (ani.equals("6") || ani.equals("7")) {
            if (undermap[x][y] == '1') {
                if (player) {
                    switch (move) {
                        case ("w"):
                            if (animals[x][y] == '1') {
                                System.out.println("水中有老鼠，过不去");
                                x = 9;
                                y = 9;
                            }
                            if (animals[x - 1][y] == '1') {
                                System.out.println("水中有老鼠，过不去");
                                x = 9;
                                y = 9;
                            }
                            x = x - 2;
                            if (0 == comparestrength(player, animals[x + 3][y], animals[x][y], x, y, undermap)) {
                                return temp;
                            }
                            break;
                        case ("a"):
                            if (animals[x][y] == '1') {
                                System.out.println("水中有老鼠，过不去");
                                x = 9;
                                y = 9;
                            }
                            if (animals[x][y - 1] == '1') {
                                System.out.println("水中有老鼠，过不去");
                                x = 9;
                                y = 9;
                            }
                            if (animals[x][y - 2] == '1') {
                                System.out.println("水中有老鼠，过不去");
                                x = 9;
                                y = 9;
                            }
                            y = y - 3;
                            if (0 == comparestrength(player, animals[x][y + 4], animals[x][y], x, y, undermap)) {
                                return temp;
                            }
                            break;
                        case ("s"):
                            if (animals[x][y] == '1') {
                                System.out.println("水中有老鼠，过不去");
                                x = 9;
                                y = 9;
                            }
                            if (animals[x + 1][y] == '1') {
                                System.out.println("水中有老鼠，过不去");
                                x = 9;
                                y = 9;
                            }
                            x = x + 2;
                            if (0 == comparestrength(player, animals[x - 3][y], animals[x][y], x, y, undermap)) {
                                return temp;
                            }
                            break;
                        case ("d"):
                            if (animals[x][y] == '1') {
                                System.out.println("水中有老鼠，过不去");
                                x = 9;
                                y = 9;
                            }
                            if (animals[x][y + 1] == '1') {
                                System.out.println("水中有老鼠，过不去");
                                x = 9;
                                y = 9;
                            }
                            if (animals[x][y + 2] == '1') {
                                System.out.println("水中有老鼠，过不去");
                                x = 9;
                                y = 9;
                            }
                            y = y + 3;
                            if (0 == comparestrength(player, animals[x][y - 4], animals[x][y], x, y, undermap)) {
                                return temp;
                            }
                    }
                } else {
                    switch (move) {
                        case ("w"):
                            if (animals[x][y] == 'a') {
                                System.out.println("水中有老鼠，过不去");
                                return temp;
                            }
                            if (animals[x - 1][y] == 'a') {
                                System.out.println("水中有老鼠，过不去");
                                return temp;
                            }
                            x = x - 2;
                            if (0 == comparestrength(player, animals[x + 3][y], animals[x][y], x, y, undermap)) {
                                return temp;
                            }
                            break;
                        case ("a"):
                            if (animals[x][y] == 'a') {
                                System.out.println("水中有老鼠，过不去");
                                return temp;
                            }
                            if (animals[x][y - 1] == 'a') {
                                System.out.println("水中有老鼠，过不去");
                                return temp;
                            }
                            if (animals[x][y - 2] == 'a') {
                                System.out.println("水中有老鼠，过不去");
                                return temp;
                            }
                            y = y - 3;
                            if (0 == comparestrength(player, animals[x][y + 4], animals[x][y], x, y, undermap)) {
                                return temp;
                            }
                            break;
                        case ("s"):
                            if (animals[x][y] == 'a') {
                                System.out.println("水中有老鼠，过不去");
                                return temp;
                            }
                            if (animals[x + 1][y] == 'a') {
                                System.out.println("水中有老鼠，过不去");
                                return temp;
                            }
                            x = x + 2;
                            if (0 == comparestrength(player, animals[x - 3][y], animals[x][y], x, y, undermap)) {
                                return temp;
                            }
                            break;
                        case ("d"):
                            if (animals[x][y] == 'a') {
                                System.out.println("水中有老鼠，过不去");
                                return temp;
                            }
                            if (animals[x][y + 1] == 'a') {
                                System.out.println("水中有老鼠，过不去");
                                return temp;
                            }
                            if (animals[x][y + 2] == 'a') {
                                System.out.println("水中有老鼠，过不去");
                                return temp;
                            }
                            y = y + 3;
                            if (0 == comparestrength(player, animals[x][y - 4], animals[x][y], x, y, undermap)) {
                                return temp;
                            }
                    }
                }


            }
        }
        if (!(ani.equals("6") || ani.equals("7") || ani.equals("1"))) {
            if (undermap[x][y] == '1') {
                System.out.println("该动物不能下水");
                return temp;
            }
        }
        if (player && undermap[x][y] == '3') {
            System.out.println("自己的动物不能走入自己的兽穴");
            return temp;
        }
        if (!player == true && undermap[x][y] == '5') {
            System.out.println("自己的动物不能走入自己的兽穴");
            return temp;
        }
        temp[0] = x;
        temp[1] = y;
        return temp;
    }

    //特殊移动判断
    public static int specialjudge(String ani, int x, int y, char[][] animals, char tempmap) {
        if (ani.equals("1") && tempmap == '1') {
            if (animals[x][y] != '0' && animals[x][y] != '1' && animals[x][y] != 'a') {
                System.out.println("老鼠下水张不开嘴。.。");
                return 0;
            }
        }
        return 1;
    }

    //胜负判定
    public static void winJudge(char[][] animals) {
        int x = 0, y = 0;
        if (animals[3][0] != '0') {
            System.out.println("P2玩家胜!");
            System.exit(0);
        }

        if (animals[3][8] != '0') {
            System.out.println("P1玩家胜!");
            System.exit(0);
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (animals[i][j] > 48 && animals[i][j] < 57)
                    x++;
                if (animals[i][j] > 96 && animals[i][j] < 105)
                    y++;
            }
        }
        if (y == 0) {
            System.out.println("P1玩家胜！");
            System.exit(0);
        }
        if (x == 0) {
            System.out.println("P2玩家胜！");
            System.exit(0);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File tile = new File("tile.txt");
        Scanner data1 = new Scanner(tile);
        char[][] undermap = new char[7][9];
        char[][] animals = new char[7][9];
        boolean player = true;
        String dataMap = " ";
        int x, y, tempx, tempy;
        char temp;
        int backstep = 0;
        int currentstep = 0;
        File animal = new File("animal.txt");
        Scanner data2 = new Scanner(animal);
        String dataAnimal = " ";
        char[][][] Memory = new char[10086][7][9];
        for (int i = 0; i < 7; i++) {
            dataMap = data1.next();
            dataAnimal = data2.next();
            for (int j = 0; j < 9; j++) {
                undermap[i][j] = dataMap.charAt(j);
                animals[i][j] = dataAnimal.charAt(j);
            }
        }
        Scanner scanner = new Scanner(System.in);
        print(animals, undermap);
        Memory[0] = copyArray(animals);
        for (int i = 1; i <= 10086; i++) {
            String step = null;
            if (player) System.out.println("请左方玩家下棋");
            else System.out.println("请右方玩家下棋");
            step = scanner.nextLine();
            if (step.equals("restart")) {
                currentstep = 0;
                backstep = 0;
                animals = copyArray(Memory[0]);
                player = true;
                continue;
            }
            if (step.equals("undo")) {
                if (currentstep < 1) {
                    System.out.println("invalid undo");
                    continue;
                }
                animals = copyArray(Memory[currentstep - 1]);
                currentstep--;
                backstep++;
                print(animals, undermap);
                player = !player;
                continue;
            }
            if (step.equals("redo")) {
                if (backstep < 1) {
                    System.out.println("invalid redo");
                    continue;
                }
                animals = copyArray(Memory[currentstep + 1]);
                currentstep++;
                backstep--;
                print(animals, undermap);
                player = !player;
                continue;
            }
            int help = help(step, animals, undermap, Memory);
            if (help == 0) continue;
            if (step.length() == 0 || step.length() == 1) {
                System.out.println("啊呀，指令不全呦");
                continue;
            }
            String ani = step.substring(0, 1);
            String move = step.substring(1, 2);
            char first = step.charAt(0);
            if (first > '8' || first < '1') {
                System.out.println("不合法的指令");
                continue;
            }
            if (judgecommand(step, ani, move) == 0) continue;
            if (player == true) {
                x = findl(ani, animals)[0];
                y = findl(ani, animals)[1];
                if (x == 9)
                    continue;
                temp = animals[x][y];
                tempx = x;
                tempy = y;
                x = normalmove(ani, move, tempx, tempy, animals, undermap, player)[0];
                if (x == 9)
                    continue;
                y = normalmove(ani, move, tempx, tempy, animals, undermap, player)[1];
                animals[tempx][tempy] = '0';
                animals[x][y] = temp;
                currentstep++;
                Memory[currentstep] = copyArray(animals);
                player = !player;
            } else {
                x = findr(ani, animals)[0];
                if (x == 9)
                    continue;
                y = findr(ani, animals)[1];
                temp = animals[x][y];
                tempx = x;
                tempy = y;
                x = normalmove(ani, move, tempx, tempy, animals, undermap, player)[0];
                if (x == 9)
                    continue;
                y = normalmove(ani, move, tempx, tempy, animals, undermap, player)[1];
                animals[tempx][tempy] = '0';
                animals[x][y] = temp;
                currentstep++;
                Memory[currentstep] = copyArray(animals);
                winJudge(animals);
                player = !player;
            }
            backstep = 0;
            print(animals, undermap);
        }
    }
}
