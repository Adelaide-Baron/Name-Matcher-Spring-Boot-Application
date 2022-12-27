public class practise {


    public static void main(String[] args) {
        int[] b = {1,2,3};
        int[] a = {1,4,6};

        StringBuffer bBuffer = new StringBuffer();
//        StringBuffer aBuffer = new StringBuffer();


        for(int number : b){
            bBuffer.append(Integer.toString(number));
        }
//        for(int number : a){
//            aBuffer.append(Integer.toString(number));
//        }
//        System.out.println(bBuffer);

        String bString = bBuffer.toString();


        for(int number : a){
            if (bString.contains(Integer.toString(number))){
                int index = bString.indexOf(Integer.toString(number));
                //check for other occurences
                int indexx = bString.indexOf(Integer.toString(number), index);
                System.out.println(indexx);
            }
        }
    }
}
