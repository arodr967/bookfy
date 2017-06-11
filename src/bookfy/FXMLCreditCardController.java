   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;
import java.util.*;

/**
 * FXML Controller class
 *
 * @author jesus
 */
public class FXMLCreditCardController implements Initializable {
    private CreditCard activeCard; //added by Roger Boza
    @FXML
  
    private JFXButton btnAction;
    @FXML
    private JFXButton btnDeleteCard;

    //Roger Boza
    public FXMLCreditCardController(CreditCard activeCard) {
        this.activeCard = activeCard;
    }

    public FXMLCreditCardController() {
        //nothing for now
    }//end Roger Boza
    
    
    @FXML
    private JFXComboBox<String> comboMonth;
    @FXML
    private JFXComboBox<String> comboYear;
    @FXML
    private JFXTextField nameCreditCard;
    @FXML
    private JFXTextField cardNumber;
    @FXML
    private JFXTextField addressCreditCard;
    @FXML
    private JFXTextField adress2CreditCard;
    @FXML
    private JFXTextField ccvCredit;
    @FXML
    private JFXTextField cityCreditCard;
    @FXML
    private JFXTextField stateCreditCard;
    @FXML
    private JFXTextField countryCreditCard;
    
    //Validations
    //all of them are true if the address is in a good format
    
    //name
    boolean isName = true;
    
    //ccv
    boolean ccvValidation = true;
    
    //address
    boolean controlA = true;
    
    //optional address
    boolean optAddressA = true;
    
    //city validation
    boolean cityValidation = true;
    
    //state validation
    boolean stateValidation = true;
    
    //country
    boolean countryValidation = true;
    
    //credit card
    boolean creditValidation = true;
    
    //credit card helper (luhn algorithm)
    ArrayList<Integer> odd_arraylist = new ArrayList<Integer>();	
    ArrayList<Integer> even_arraylist = new ArrayList<Integer>();
    /**
     * Lazaro Fernandez
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Roger Boza
        //Check if its update or add new
        if(activeCard != null){//Update
            nameCreditCard.setText(activeCard.getName());
            cardNumber.setText(activeCard.getNumber());
            ccvCredit.setText(activeCard.getCvv());
            String[] exp = activeCard.getExpiration().split("/");
            comboMonth.setValue(numberToMonth(exp[0]));
            comboYear.setValue(exp[1]);
            addressCreditCard.setText(activeCard.getBilling().getAddressLine1());
            adress2CreditCard.setText(activeCard.getBilling().getAddressLine2());
            cityCreditCard.setText(activeCard.getBilling().getCity());
            stateCreditCard.setText(activeCard.getBilling().getState());
            countryCreditCard.setText(activeCard.getBilling().getCountry());
            btnAction.setText("UPDATE");
        }
        else{
            btnDeleteCard.setVisible(false);
            btnAction.setText("SAVE");
        }//end Roger Boza
        comboMonth.getItems().addAll("January","February","March", "April", "May", "June",
                "July",  "August", "September", "October", "November", "December");
        
        comboYear.getItems().addAll("2017","2018","2019","2020","2021","2022","2023",
                        "2024","2025","2026","2027","2028","2029","2030");        
    }    
         
//Roger Boza
    private String monthToNumber(String month){
        switch(month){
            case "January":
                return "01";
            case "February":
                return "02";
            case "March":
                return "03";
            case "April":
                return "04";
            case "May":
                return "08";
            case "June":
                return "06";
            case "July":
                return "07";
            case "August":
                return "08";
            case "September":
                return "09";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
            default:
                return "-1";
        }
    }
    
    private String numberToMonth(String month){
        switch(month){
            case "01":
                return "January";
            case "02":
                return "February";
            case "03":
                return "March";
            case "04":
                return "April";
            case "05":
                return "May";
            case "06":
                return "June";
            case "07":
                return "July";
            case "08":
                return "August";
            case "09":
                return "September";
            case "10":
                return "October";
            case "11":
                return "November";
            case "12":
                return "December";
            default:
                return "-1";
        }
    }
    //end Roger Boza
    
    
    
    @FXML
    private void saveButtonMethod(ActionEvent event) {
        
        isName = true;
        ccvValidation = true;
        controlA = true;
        optAddressA = true;
        cityValidation = true;
        stateValidation = true;
        countryValidation = true;
        creditValidation = true;
        
        
        //get the name os the customer
        String[] nameVerification = nameCreditCard.getText().split(" ");

        if(nameVerification.length<1){
            isName = false;
            JOptionPane.showMessageDialog(null,"Name should be entered");
        }else if(nameVerification.length==1){
                //company name
     
                if(nameVerification[0].equalsIgnoreCase("")){
                    isName = false;
                    JOptionPane.showMessageDialog(null,"Error, Name is empty");
                }
                if(nameVerification[0].length()>=10){
                
                    //error, the name should only contain less than 10 characters
                    isName = false;
                    JOptionPane.showMessageDialog(null,"First Name Has Too many words");
                }             
                
                for(int k=0;k<nameVerification[0].length();k++){
                
                    char f = nameVerification[0].charAt(k);
                
                    if(!Character.isLetter(f)){
                        JOptionPane.showMessageDialog(null,"first name should only contain letters");
                        isName = false;
                        break;
                    }
                } //end of for
       
        }else{
        
            if(nameVerification.length != 2){
                //error the name is of the format name_lastname_others 
                //which is wrong
                isName = false;
                JOptionPane.showMessageDialog(null,"Please write your Name And Last Name");
            }else{
            
                if(nameVerification[0].length()>=10 || nameVerification[1].length()>=10){
                
                    //error, the name should only contain less than 10 characters
                    isName = false;
                    JOptionPane.showMessageDialog(null,"first name has too many words");
                }             
                
                for(int k=0;k<nameVerification[0].length();k++){
                
                    char f = nameVerification[0].charAt(k);
                
                    if(!Character.isLetter(f)){
                        JOptionPane.showMessageDialog(null,"first name should only contain letters");
                        isName = false;
                        break;
                    }
                } //end of for 1
            
                for(int k=0;k<nameVerification[1].length();k++){
                
                    char f = nameVerification[1].charAt(k);
                
                    if(!Character.isLetter(f)){
                        JOptionPane.showMessageDialog(null,"last name should only contain letters");
                        isName = false;
                        break;
                    }
                } //end of for 2
            } //end of else 
        }//end of else(big) ((nameVerification))
 
        //if(isName){
          //JOptionPane.showMessageDialog(null,"hey isName is true");  
        //}
            
        
        //ccv code check
        if(ccvCredit.getText().length()<1){
            ccvValidation = false;
            JOptionPane.showMessageDialog(null,"CCV must be entered");
        }else{
            if(ccvCredit.getText().length() != 3){
                ccvValidation = false;
                JOptionPane.showMessageDialog(null,"CCV are just 3 numbers");
  
            }else{
                char a = ccvCredit.getText().charAt(0);
                char b = ccvCredit.getText().charAt(1);
                char c = ccvCredit.getText().charAt(2);
            
                if(!(Character.isDigit(a) && Character.isDigit(b) && Character.isDigit(c))){
                    //if one of the ccv numbers is a letter or symbol, then is NOT valid
                    ccvValidation = false;
                    JOptionPane.showMessageDialog(null,"CCV are the last 3 numbers of your card");
                }        
            } //end of else (small)
        } //end of else (big) (ccv)
        
        //if(ccvValidation){
          //  JOptionPane.showMessageDialog(null,"CCV is GOOD");
        //}
        
        //address verification
        
        String[] addressVerification = addressCreditCard.getText().split(" ");
        
        if(addressVerification.length<1){
            controlA = false;
            JOptionPane.showMessageDialog(null,"You must enter your address");
        }else{
        
            if(addressVerification.length == 3 || addressVerification.length==4){
                  
                //JOptionPane.showMessageDialog(null,"hey");
                String test = "~ ! @ # $ % ^ & * ( ) _ + - = `";
                
                for(int i=0;i<addressVerification.length;i++){
                    
                    String tmp = addressVerification[i];  //86
                    //JOptionPane.showMessageDialog(null, "tmp is: " + tmp + ":");
                    
                    if(tmp.length()<=1){
                        JOptionPane.showMessageDialog(null, "Word in address are too short");
                        controlA = false;
                        break;
                    }
                    
                    if(tmp.length()>6){
                        JOptionPane.showMessageDialog(null, "Words in address are too LONG");
                        controlA = false;
                        break;
                    }
                    for(int k=0;k<addressVerification[i].length();k++){
                        
                        char partition = tmp.charAt(k); //8 6
                        //JOptionPane.showMessageDialog(null, "partition: " + partition);
                        
                        int h = test.indexOf(partition);
                        
                        if(h != -1){
                            JOptionPane.showMessageDialog(null,"Error: No Symbols Allowed in Address");
                            controlA = false;
                            break;
                        }else{
                            //the first word in address must be a NUMBER
                            if(i==0 && (!Character.isDigit(partition))){
                                controlA = false;
                                break;
                            }
                            if(i==1 && (!Character.isLetter(partition))){
                                controlA = false;
                                break;
                            }
                        }
                    } //end of for (inner)
                } //end of for (outer)
            }else{
               //address is not in a good format because doesn't have length 3 or 4
               JOptionPane.showMessageDialog(null,"Address is Not in a good format");
               controlA=false;
            }
        }//end of else (big)
                    
        //if(controlA){
          //  JOptionPane.showMessageDialog(null,"Good Address");
        //}
                
        
        //address (optional)
        String[] addressOptVerification = adress2CreditCard.getText().split(" ");
        
        String test = "~ ! @ $ % ^ & * ( ) _ + - = `";
        
        if(addressOptVerification.length<1){
            //address 2 is optional
            optAddressA = true;
        }else{
                
            
            if(addressOptVerification.length >3){
                JOptionPane.showMessageDialog(null, "Address2 is too long");
                optAddressA = false;
            }else{
                
                for(int i=0;i<addressOptVerification.length;i++){
                
                    String temp = addressOptVerification[i]; 
                
                    if(temp.length()>10){
                        JOptionPane.showMessageDialog(null, "Address2 is too long");
                        optAddressA = false;
                    }
                    
                    for(int k=0;k<temp.length();k++){
                        
                        char partition = temp.charAt(k);    
                        int h = test.indexOf(partition);
                        
                        //no symbols except # are allowed
                        if(h != -1){
                          
                            JOptionPane.showMessageDialog(null,"Invalid Address2");
                            optAddressA = false;
                            break;
                        } 
                    } //end of for (inner)   
                 
                }  //end of for (outer)               
            } //end of else (address 2)    
        } //end of else (big)

        //if(optAddressA){
            //JOptionPane.showMessageDialog(null,"Good Optional Address");
        //}
        
        //city validation
        String[] cityVerification = cityCreditCard.getText().split(" ");
        
        if(cityVerification.length < 1){
            
            cityValidation = false;
            JOptionPane.showMessageDialog(null,"Invalid City Formatt");
        }else{
              
                if(cityVerification.length>=3){
                    cityValidation = false;
                    JOptionPane.showMessageDialog(null,"City has too many letters");
                }
                
                for(int i=0;i<cityVerification.length;i++){
                    
                    String temp = cityVerification[i];
                    //JOptionPane.showMessageDialog(null,"this is temp: " + temp);
                    
                    if(temp.length()<1 || temp.length()>13){
                        cityValidation = false;
                        JOptionPane.showMessageDialog(null,"City is empty or too long");
                    }
                    
                    for(int k=0;k<temp.length();k++){
                        
                         if(!Character.isLetter(temp.charAt(k))){
                            //error city have either numbers or symbols
                            JOptionPane.showMessageDialog(null,"city has symbols/numbers");
                            cityValidation = false;
                        }      
                    }
                } //end of for
        }
        
        //if(cityValidation){
          //  JOptionPane.showMessageDialog(null,"city is valid");
        //}
        
        //state
        String[] stateVerification = stateCreditCard.getText().split(" ");
        
        //JOptionPane.showMessageDialog(null,stateVerification.length);
        
        if(stateVerification.length <1){
           
                JOptionPane.showMessageDialog(null,"state field is required");
                stateValidation = false;
        }else{
            
                if(stateVerification.length>=3){
                    
                    stateValidation = false;
                    JOptionPane.showMessageDialog(null,"State has too many letters");
                }else{
                                   
                        for(int i=0;i<stateVerification.length;i++){
                    
                           String temp = stateVerification[i];

                           if(temp.length()<1 || temp.length()>13){
                               stateValidation = false;
                                JOptionPane.showMessageDialog(null,"State is empty or too long");
                           }
                           
                           for(int k=0;k<temp.length();k++){
                                
                                if(!Character.isLetter(temp.charAt(k))){
                                    //error state have either numbers or symbols
                                    JOptionPane.showMessageDialog(null,"state has symbols/numbers");
                                    stateValidation = false;
                                }      
                           } //end of for inner
                        }   
                }
        } //end of else (big)
            
        //if(stateValidation){
                //       JOptionPane.showMessageDialog(null,"state is valid");    
        //}
        
        //country
        String[] countryVerification = countryCreditCard.getText().split(" ");
        
        //JOptionPane.showMessageDialog(null,countryVerification[0] + "----" + countryVerification.length);
        
        if(countryVerification.length < 1){
           
           JOptionPane.showMessageDialog(null,"Wrong Country format");
           countryValidation = false;
        }else{
        
            for(int i=0;i<countryVerification.length;i++){
                
                String temp = countryVerification[i];
  
                if(temp.length() < 1 || temp.length()>13){
                    JOptionPane.showMessageDialog(null,"country field is empty or too long");
                    countryValidation = false;
                }    
                for(int k=0;k<temp.length();k++){
                
                        char f = temp.charAt(k);
                
                        if(!Character.isLetter(f)){
                            JOptionPane.showMessageDialog(null,"country name should have letters ONLY");
                            countryValidation = false;
                            break;
                        }
                } //end of for (inner) 
           } //end of else (inner)          
        } //end of for (outer)
        
        //if(countryValidation){
          //                  JOptionPane.showMessageDialog(null,"country is valid");
        //}
        
        //credit card verification
        //variables (tools)
	String creditCardNumber = cardNumber.getText();
        
        for(int i=0;i<creditCardNumber.length();i++){
            
            char rev = creditCardNumber.charAt(i);
            
            if(!Character.isDigit(rev)){
               JOptionPane.showMessageDialog(null,"Only Numbers Are Allowed in Credit Card Number");
               creditValidation = false;
            }
        }
        if(creditValidation){
            Integer[] numbers = new Integer[creditCardNumber.length()];
            int total_numbers = 0;
            boolean isTrue= true;
		
            if(creditCardNumber.length()!=16){
                JOptionPane.showMessageDialog(null, "Sorry, Card is NOT valid");
                creditValidation = false;
            }else{		
                //converting into integer array so we can do calculations later
                for(int i=0;i<creditCardNumber.length();i++){
				
                    numbers[i] = Integer.parseInt(creditCardNumber.substring(i, i+1));		
                }
            
                //separating numbers and adding them in order to lists
                for(int i=0;i<numbers.length;i++){
				
                    if(isTrue){
                        even_arraylist.add(numbers[i]);
                        isTrue=false;
                    }else{
                        odd_arraylist.add(numbers[i]);
                        isTrue=true;
                    }
				
                }//end of loop

                //converting lists to arrays so we can apply 
                //the 3rd step of luhn's algorithm
                Integer[] odd = odd_arraylist.toArray(new Integer[odd_arraylist.size()]);	
                Integer[] even = even_arraylist.toArray(new Integer[even_arraylist.size()]);
		
                //double the array of left side=(even side only)
                for(int i=0;i<even.length;i++){
				
                    if(even[i]>=5){
				
                        even[i] = even[i]*2;	
                        String temp = even[i].toString();
                        int one = Integer.parseInt(temp.substring(0,1));
                        int two = Integer.parseInt(temp.substring(1));	
                        even[i] = one+two;
				
                    }else{
                        even[i] = even[i]*2;
                    }
                }
			
                //add even+odd (all numbers added)
			
                for(int x=0;x<even.length;x++){
				
                    total_numbers += even[x];
                }
			
                for(int y=0;y<odd.length;y++){
				
                    total_numbers += odd[y];
                }
			
                if(total_numbers%10==0){
				
                    JOptionPane.showMessageDialog(null, "Credit Card IS Valid");
		
                }else{
                    JOptionPane.showMessageDialog(null, "Credit Card is NOT Valid");
                    creditValidation = false;
                }
            } //end of else(big)
        
            /*if(isName && ccvValidation && controlA && controlB && controlC && controlD && optAddressA
                && optAddressB && optAddressC && optAddressD && cityValidation && stateValidation 
                && countryValidation){*/
        
            //Roger Boza
            if(isName && ccvValidation && controlA && cityValidation && stateValidation && countryValidation){

                if(btnAction.getText().equalsIgnoreCase("update")){//update card  
                    Address billing = new Address(nameCreditCard.getText(), addressCreditCard.getText(), adress2CreditCard.getText(), cityCreditCard.getText(), stateCreditCard.getText(), 0, countryCreditCard.getText());
                    CreditCard newCard = new CreditCard(activeCard.getcCID(), nameCreditCard.getText(), creditCardNumber, monthToNumber(comboMonth.getValue()) + "/" + comboYear.getValue(), ccvCredit.getText(), billing);
                    newCard.updateCardInDB();

                    Bookfy.getUser().loadCreditCards();
                    Bookfy.getMainWindowController().displayViewProfile();
                }else{//save card
                    Address billing = new Address(nameCreditCard.getText(), addressCreditCard.getText(), adress2CreditCard.getText(), cityCreditCard.getText(), stateCreditCard.getText(), 0, countryCreditCard.getText());
                    CreditCard newCard = new CreditCard(0, nameCreditCard.getText(), creditCardNumber, monthToNumber(comboMonth.getValue()) + "/" + comboYear.getValue(), ccvCredit.getText(), billing);
                    newCard.saveCardIntoDB();

                    Bookfy.getUser().loadCreditCards();
                    Bookfy.getMainWindowController().displayViewProfile();
                }//end Roger Boza
            
            }
        }//end of creditvalidation 
    } //end of method (saveButtonMethod)
    
    //Roger Boza
    @FXML
    private void cancel(ActionEvent event) {
        Bookfy.getMainWindowController().displayViewProfile();
    }

    @FXML
    private void deactivate(ActionEvent event) {
        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this credit card? This cannot be undone later.", "Bookfy", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION){
            activeCard.deactivate();
            JOptionPane.showMessageDialog(null, "The credit card has been deleted.", "Bookfy", JOptionPane.INFORMATION_MESSAGE);
            
            Bookfy.getUser().loadCreditCards();
            Bookfy.getMainWindowController().displayViewProfile();
        }   
    }//end Roger Boza
            
} //end of class