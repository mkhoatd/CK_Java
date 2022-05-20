import java.util.*;

public class XuLyDonHang {
    private final List<String[]> dataImported;
    public XuLyDonHang(List<String[]> data){
        dataImported=data;
    }
    public int getSoLuong(String tenMatHang){
        int sum=0;
        for (String[] x : dataImported) {
            if(Objects.equals(x[0], tenMatHang)) sum++; //tuong duong x[0]==tenMatHang
        }
        return sum;
    }
    public int getTongTien(String tenNguoiMua){
        int sum=0;
        for(String[] x:dataImported){
            if(Objects.equals(x[2], tenNguoiMua)) sum+=Integer. parseInt(x[1]);
        }
        return sum;
    }
    public List<String> getTenNguoiMua(String tenMatHang){
        List<String> result=new ArrayList<>();
        for(String[] x:dataImported){
            if(Objects.equals(x[0], tenMatHang)) result.add(x[2]);
        }
        return result;
    }
    public List<String> getGoiY(String tenMatHang){
        List<String> result=new ArrayList<>();
        List<String> tenNguoiMua=getTenNguoiMua(tenMatHang);        //ten nguoi mua mat hang
        Map<String, Integer> map= new HashMap<>();     //map tuc la cac key-value
        for(String[] x:dataImported){
            if(tenNguoiMua.contains(x[2])){                         //neu 1 mat hang co chung nguoi mua voi mat hang kia
                if(!map.containsKey(x[0])) {                        //neu map khong co key la nguoi mua
                    map.put(x[0], Integer.valueOf(x[1]));           //them key vao, so tien la gia cua mat hang
                }
                else map.replace(x[0],map.get(x[0])+Integer.parseInt(x[1]));    //neu da co thi + them tien
            }
        }
        map.remove(tenMatHang);
        map.forEach((key, value) -> result.add(key+", "+value.toString()));
        //tuong duong voi
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            String key = entry.getKey();
//            Integer value = entry.getValue();
//            result.add(key + ", " + value.toString());
//        }
        return result;
    }
}
