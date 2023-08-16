import java.util.Scanner;

class game
{
    int[][] grid;
    int count = 0;
    
    game() {
        grid = new int[][] {{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }};
    }


    
    public void display() {
        String[] units = { "-", "O", "X" };
        System.out.println("============");
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(" "+units[cell]);
            }
            System.out.print("\n");
        }
        System.out.println("============");
    }
    
    public void takeinput(int player) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player " + player + "'s turn: ");
        try{
            String[] split = scanner.nextLine().split(" ", 2);
            int[] array = new int[2];
            for (int i = 0; i < 2; ++i) {
                array[i] = Integer.parseInt(split[i]);
            }
            if(grid[array[0]-1][array[1]-1] == 0){
                grid[array[0]-1][array[1]-1] = player;
            }
            else{
                System.out.println("Value already exists! retry..");
                takeinput(player);
            }
        }catch(Exception e){
            System.out.println("Input format is '{x} {y}' where 1 <= x,y <= 3 .. eg. '2 3'.");
            takeinput(player);
        }
       
    }
    int getValue(int[] coordinate){
        return grid[coordinate[0]][coordinate[1]];
    }
    int check(){
        int [][][]checklist = {{{0,0},{0,1},{0,2}},{{1,0},{1,1},{1,2}},{{2,0},{2,1},{2,2}},{{0,0},{1,0},{2,0}},{{0,1},{1,1},{2,1}},{{0,2},{1,2},{2,2}},{{0,0},{0,1},{0,2}},{{0,0},{1,1},{2,2}},{{2,0},{1,1},{0,2}}};
        for(int[][]checkline : checklist){
            if(getValue(checkline[0]) == getValue(checkline[1]) && getValue(checkline[1]) == getValue(checkline[2])  && getValue(checkline[2]) != 0){
                return getValue(checkline[0]);
            }
        }
        return 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int flag = 0;
        for (int i = 0; i < 9; ++i) {
            display();
            count += 1;
            takeinput((i % 2)+ 1);
            int checkval = check();
            if(checkval!=0){
                display();
                System.out.println("Player " + checkval + " won!");
                flag = 1;
                break;
            }
        }
        if (flag == 0){
            display();
            System.out.println("No one won! :(");
        }
        System.out.print("Play again? (y/n): ");
        String answer = scanner.nextLine();    
        if(answer.equals("y")){
            restart();
        }

    }
    public void restart(){
        grid = new int[][]{{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }};
        start();
    }
}

class tictactoe{
	public static void main(String[] args){
		game grid = new game();
		grid.start();
	}
}
