/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setting;

/**
 *
 * @author imam
 */
public class query {
    
    public String query(String query){
        return query;
    }
    
    public String insert(String tabel, String[][] field){
        String sql = "INSERT INTO "+tabel;
        for (int i = 0; i < field.length; i++) {
            if (i == 0) {
                sql += " ("+field[i][0]+", ";
            }else if(i == field.length - 1){
                sql += field[i][0]+") VALUES ";
            }else{
                sql += field[i][0]+", ";
            }
        }
        for (int i = 0; i < field.length; i++) {
            if (i == 0) {
                sql += "('"+field[i][1]+"', ";
            }else if(i == field.length - 1){
                sql += "'"+field[i][1]+"')";
            }else{
                sql += "'"+field[i][1]+"', ";
            }
        }
        return sql;
    }
    
    public String update(String tabel, String[][] field){
        String sql = "UPDATE "+tabel+" SET ";
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (j == field[i].length - 1) {
                    if (i == field.length - 1) {
                        sql += "'"+field[i][j]+"'";
                    }else{
                        sql += "'"+field[i][j]+"', ";
                    }
                }else{
                    sql += field[i][j]+" = ";
                }
            }
        }
        return sql;
    }
    
    public String delete(String tabel, String[] field){
        String sql = "DELETE FROM "+tabel+" WHERE "+field[0]+" = '"+field[1]+"'";
        return sql;
    }
    
    public String delete(String tabel){
        String sql = "DELETE FROM "+tabel;
        return sql;
    }
    
    public String select(String field){
        String sql = "SELECT "+field;
        return sql;
    }
    
    public String select(String[] field){
        String sql = "SELECT ";
        for (int i = 0; i < field.length; i++) {
            if (i == field.length - 1) {
                sql += field[i];
            }else{
                sql += field[i]+", ";
            }
        }
        return sql;
    }
    
    public String from(String tabel){
        String sql = " FROM "+tabel;
        return sql;
    }
    
    public String where(String field, String value){
        String sql = " WHERE "+field+" = '"+value+"'";
        return sql;
    }
    
    public String where_and(String tabel, String field){
        String sql = " AND "+tabel+" = '"+field+"'";
        return sql;
    }
    
    public String order_by(String field, String orderBy){
        String sql = " ORDER BY "+field+" "+orderBy;
        return sql;
    }
    
    public String join(String tabel, String relasi){
        String sql = " JOIN "+tabel+" ON "+relasi;
        return sql;
    }
    
    public String group_by(String field){
        String sql = " GROUP BY "+field;
        return sql;
    }
    
    public String date_format(String field, String format){
        String sql = "DATE_FORMAT("+field+", '"+format+"') AS "+field;
        return sql;
    }
    
    public String where_not_in(String field, String[] value){
        String sql = " WHERE "+field+" NOT IN ";
        if (value.length == 1) {
            sql += "('"+value[0]+"')";
        }else{
            for (int i = 0; i < value.length; i++) {
                if (i == 0) {
                    sql += "( '"+value[i]+"', ";
                }else if(i == value.length - 1){
                    sql += "'"+value[i]+"' )";
                }else{
                    sql += "'"+value[i]+"', ";
                }
            }
        }
        return sql;
    }
    
    public String format(String field, int koma){
        String sql = "FORMAT("+field+","+koma+") AS "+field;
        return sql;
    }
    
    public String concat(String[] field){
        String sql = " CONCAT (";
        for (int i = 0; i < field.length; i++) {
            if (i == field.length - 1) {
                sql += field[i]+") AS "+field[0];
            }else{
                sql += field[i]+", ";
            }
        }
        return sql;
    }
    
    public String min(String field){
        String sql = "MIN("+field+") AS "+field;
        return sql;
    }
    
    public String max(String field){
        String sql = "MAX("+field+") AS "+field;
        return sql;
    }
    
    public static void main(String[] args) {
        query db = new query();
        String[][] data = {
            {"kode_gudang","123"},
            {"nama_gudang","abc"},
            {"alamat","qwerty"},
            {"status_gudang","cabang"}
        };
        String sql = db.insert("gudang", data);
        System.out.println("query insert : "+sql);
        
        String[][] data2 = {
            {"nama_gudang","abc"},
            {"alamat","qwerty"},
            {"status_gudang","pusat"}
        };
        sql = db.update("gudang", data2);
        sql += db.where("kode_gudang", "123");
        System.out.println("query update : "+sql);
        
        String[] data3 = {"kode_gudang","123"};
        sql = db.delete("gudang", data3);
        System.out.println("query delete : "+sql);
        
        sql = db.delete("gudang");
        sql += db.where("kode_gudang", "123");
        System.out.println("query delete 2 : "+sql);
        
        sql = db.select("*");
        sql += db.from("gudang");
        System.out.println("query select : "+sql);
        
        sql = db.select("*");
        sql += db.from("gudang");
        sql += db.where("kode_gudang", "123");
        System.out.println("query select 2 : "+sql);
        
        String[] field = {"nama_gudang","alamat","status_gudang"};
        sql = db.select(field);
        sql += db.from("gudang");
        sql += db.where("kode_gudang", "123");
        sql += db.where_and("nama_gudang", "abc");
        sql += db.order_by("nama_gudang", "asc");
        System.out.println("query select 3 : "+sql);
        
        sql = db.select("*");
        sql += db.from("stok");
        sql += db.join("barang", "barang.kode_barang=stok.kode_barang");
        sql += db.join("gudang", "gudang.kode_gudang=stok.kode_gudang");
        System.out.println("query join : "+sql);
        
        sql = db.select("*");
        sql += db.from("barang");
        sql += db.group_by("nama_barang");
        System.out.println("query group by : "+sql);
        
        sql = db.select("transaksipusat.*, ");
        sql += db.date_format("tanggal_transaksi", "%d %M %Y");
        sql += db.from("transaksipusat");
        System.out.println("query date format : "+sql);
        
        sql = db.query("select * from stok");
        System.out.println("query select 4 : "+sql);
        
        String[] data4 = {"Beras Biasa"};
        sql = db.select("*");
        sql += db.from("kategori");
        sql += db.where_not_in("nama_kategori", data4);
        System.out.println("query not in : "+sql);
        
        String[][] data5 = {
            {"nama_kategori", "abc"}
        };
        sql = db.update("kategori", data5);
        sql += db.where("kode_kategori", "123");
        System.out.println("query update 2 : "+sql);
    }
}
