package bookstore;

import java.util.ArrayList;
import java.util.Scanner;

public class BookStore {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        
        int quantity=0;
        int user_choice=2;
        boolean quit=false;
        String title = null;
        showCategories();
         do
        {
            //display menu to user
            //ask user for his choice and validate it (make sure it is between 1 and 6)
            System.out.println();
            System.out.println("Enter key to: ");
            System.out.println("1) Add a book to the stock");
            System.out.println("2) Sell a book in stock(All copies)");
            System.out.println("3) List the titles of all the books in stock (in the Bookstore object)");
            System.out.println("4) List all the information about the books in stock (in the Bookstore object)");
            System.out.println("5) Print out the gross income of the bookstore");
            System.out.println("6) print out the total vat: ");
            System.out.println("7) Show categories: ");
            System.out.println("8) Add quantity to an existing book");
            System.out.println("9) Sell a book (Some of the copies)");
            System.out.println("0) Quit");
            System.out.println();
            System.out.print("Enter choice [0-7]: ");
            user_choice = scanner.nextInt();
            switch(user_choice)
            {
                case 1: addNewBook(store);break;
                case 2: sellBook(store);break;
                case 3: showAllBooks(store);break;
                case 4: showAllInfo(store);break;
                case 5: grossIncome(store);break;
                case 6: totalVat(store);break;
                case 7: showCategories();break;
                case 8: addQuantity(store);break;
                case 9: sellSomeCopies(store);break;
                case 0: quit=true;break;//to exit the loop
                default: System.out.println("Please input value between 1-7: ");
            }
        
        
    }while(!quit);
    
         
    
    
    
    
    }
    
    
    
    public static class Store {
        private Book[] books;
        private int totalBooks;
        private ArrayList<Book> Science =new ArrayList();
        private ArrayList<Book> Literature = new ArrayList();
        private ArrayList<Book> Philosophy = new ArrayList();
        private ArrayList<Book> TotalBook = new ArrayList();
        private double totalIncome=0;
        private double totalVat=0;
        public Store()
        {
            books = new Book[1000];
            totalBooks=0;
        }

        public int getTotalBooks()
        {
            return totalBooks;
        }

        public double getTotalIncome()
        {
            return totalIncome;
        }

        public double getTotalVat()
        {
            return totalVat;
        }
        
        
        
        public void addNewBook(Book b)
        {
            if(totalBooks<books.length)
            {
                if(b.type.equalsIgnoreCase("Science"))
                {
                    Science.add(b);
                    System.out.println("Book has been added to the Science catagory");
                }
                if(b.type.equalsIgnoreCase("Literature"))
                {
                    Literature.add(b);
                    System.out.println("Book has been added to the Literature catagory");
                }
                if(b.type.equalsIgnoreCase("Philosophy"))
                {
                    Philosophy.add(b);
                    System.out.println("Book has been added to the Philosophy catagory");
                }
                books[totalBooks++]=b;
                TotalBook.add(b);
                
            }
            else
            {
                System.out.println("\nSorry, can't add book");
            }
            
        }
        public Book findABook(String title)
        {
            Book b = null;
            for (int i=0;i<totalBooks;i++)
            {
                if(books[i].title.equalsIgnoreCase(title))
                {
                   // System.out.println("locatin: "+i);
                    return books[i];
                }
            }
            return b;
        }
        public void sellBook(String title)
        {
            Book book = findABook(title);
            double income = book.price*book.quantity;
            double vat=0;
            
            totalIncome+=income;
            if(Science.contains(book))
            {
                Science.remove(book);
                System.out.println("Book->" +book.title+" is sold from science catagory");
                vat+=book.price*book.quantity*.15;
            }
            if(Philosophy.contains(book))
            {
                Philosophy.remove(book);
                System.out.println("Book->"+book.title+" is sold from in Philosophy catagory");
                vat+=book.price*book.quantity*.2;
            }
            if(Literature.contains(book))
            {
                Literature.remove(book);
                System.out.println("Book->"+book.title+" is sold from Literature catagory");
                vat+=book.price*book.quantity*.25;
            }
            TotalBook.remove(book);
            int bookPositionToDelete=0;
            for(int i=0;i<totalBooks;i++)
            {
                if(books[i].title.equalsIgnoreCase(book.title))
                {
                    bookPositionToDelete=i;
                    break;
                }
            }
            for(int i=bookPositionToDelete;i<totalBooks-1;i++)
            {
                books[i]=books[i+1];
            }
            totalBooks--;
            TotalBook.remove(book);
            totalVat+=vat;
        }
        public void showAllBooks()
        {
            for(int i=0;i<totalBooks;i++)
            {
                if(books[i]!=null)
                {
                    System.out.println(books[i].title);
                }
            }
            if(!Science.isEmpty())
            {
                System.out.println("Books in the Science catagory: ");
                for (int i=0;i<Science.size();i++)
                {
                    System.out.println(Science.get(i).title);
                }
            }
            if(!Literature.isEmpty())
            {
                System.out.println("Books in the Literature catagory: ");
                for(int i=0;i<Literature.size();i++)
                {
                    System.out.println(Literature.get(i).title);
                }
            }
            if(!Philosophy.isEmpty())
            {
                System.out.println("Books in the philosophy catagory: ");
                for(int i=0;i<Philosophy.size();i++)
                {
                    System.out.println(Philosophy.get(i).title);
                }
            }
        }
        public void showAllInfo()
        {
            int j=1;
            for(int i=0;i<totalBooks;i++)
            {
                System.out.println("Book no. "+j++);
                if(books[i]!=null)
                {
                    System.out.println("Book name: "+books[i].title);
                    System.out.println("Book category: "+books[i].type);
                    System.out.println("price: "+books[i].price);
                    System.out.println("Number of copies: "+books[i].quantity);
                }
                System.out.println();
            }
        }
        public void addQuantity(String title,int quantity)
        {
            Book book = findABook(title);
            book.quantity+=quantity;
//            Book b;
//            if(Science.contains(book))
//            {
//                for(int i=0;i<Science.size();i++)
//                {
//                    if(title.equals(Science.get(i).title))
//                    {
//                        Science.get(i).quantity+=quantity;
//                        break;
//                    }
//                }
//            }
//            if(Philosophy.contains(book))
//            {
//                for(int i=0;i<Philosophy.size();i++)
//                {
//                    if(title.equals(Philosophy.get(i).title))
//                    {
//                        Philosophy.get(i).quantity+=quantity;
//                        break;
//                    }
//                }
//            }
//            if(Literature.contains(book))
//            {
//                for(int i=0;i<Literature.size();i++)
//                {
//                    if(title.equals(Literature.get(i).title))
//                    {
//                        Literature.get(i).quantity+=quantity;
//                    }
//                }
//            }
        }
        public void sellQuantity(Book book,int quantity)
        {
            double vat=0;
            book.quantity-=quantity;
            
            if(Science.contains(book))
            {
                //Science.remove(book);
                System.out.println(quantity+" Number of book \"" +book.title+"\" is sold from science catagory");
                vat+=book.price*quantity*.15;
            }
            if(Philosophy.contains(book))
            {
                //Philosophy.remove(book);
                System.out.println(quantity+" Number of book \""+book.title+"\" is sold from in Philosophy catagory");
                vat+=book.price*quantity*.2;
            }
            if(Literature.contains(book))
            {
                //Literature.remove(book);
                System.out.println(quantity+" Number of book \""+book.title+"\" is sold from Literature catagory");
                vat+=book.price*quantity*.25;
            }
            totalVat+=vat;
        }
    }
    public static class Book {
        private String title;
        private double price;
        private int quantity;
        private String type;

        public Book(String title, double price, int quantity,String type)
        {
            this.title = title;
            this.price = price;
            this.quantity = quantity;
            this.type=type;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public double getPrice()
        {
            return price;
        }

        public void setPrice(double price)
        {
            this.price = price;
        }

        public int getQuantity()
        {
            return quantity;
        }

        public void setQuantity(int quantity)
        {
            this.quantity = quantity;
        }

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }
        
        
    }
    public static void addNewBook(Store s)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book type: ");
        String type = scanner.next();
        while(!(type.equalsIgnoreCase("Science")||type.equalsIgnoreCase("Philosophy")||type.equalsIgnoreCase("Literature")))
        {
            System.out.println("Please input correct type: (Science , Philosophy or Literature)");
            type=scanner.next();
        }
        System.out.println("Enter book title: ");
        String title = scanner.next();
        System.out.println("Enter price");
        double price = scanner.nextInt();
        System.out.println("Enter quantity");
        int quantity= scanner.nextInt();
        Book book = new Book(title,price,quantity,type);
        
        s.addNewBook(book);
    }
    public static void sellBook(Store b)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book title you want to sell: ");
        String title = scanner.next();
        Book book = b.findABook(title);
        while(book==null)
        {
            System.out.println("Book not found .. Enter book name again");
            title = scanner.next();
            book=b.findABook(title);
        }
        b.sellBook(title);
    }
    
    public static void showAllBooks(Store b)
    {
        b.showAllBooks();
    }
    public static void showAllInfo(Store b)
    {
        b.showAllInfo();
    }
    public static void grossIncome(Store b)
    {
        System.out.println("Total income: "+b.getTotalIncome());
    }
    public static void totalVat(Store b)
    {
        System.out.println("Total vat: "+b.getTotalVat());
    }
    public static void showCategories()
    {
        System.out.println("The Categories are: ");
        System.out.println(("1.Science(vat 15%)"));
        System.out.println("2.Philosophy(vat 20%)");
        System.out.println("3.Literature(vat 25%)");
    }
    public static void addQuantity(Store b)
    {
        Scanner scanner;
        scanner = new Scanner(System.in);
        System.out.println("Enter book title: ");
        String title = scanner.next();
        System.out.println("Enter quantity you want to add");
        int quantity = scanner.nextInt();
        b.addQuantity(title, quantity);
    }
    public static void sellSomeCopies(Store b)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book name: ");
        String title= scanner.next();
        Book book = b.findABook(title);
        while(book==null)
        {
            System.out.println("Book not found .. Enter book name again");
            title = scanner.next();
            book=b.findABook(title);
        }
        System.out.println("Number of copies available: "+book.quantity);
        System.out.println("Enter number of copies to sell: ");
        int quantity=scanner.nextInt();
        while(quantity>book.quantity||quantity<=0)
        {
            System.out.println("Please enter quantity in range: ");
            quantity=scanner.nextInt();
        }
        if(quantity==book.quantity)
        {
            b.sellBook(title);
        }
        else if(quantity<book.quantity&&quantity>0)
        {
            b.sellQuantity(book,quantity);
        }
    }
}