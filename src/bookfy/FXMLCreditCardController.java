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
    boolean controlB = true;
    boolean controlC = true;
    boolean controlD = true;
    
    //optional address
    boolean optAddressA = true;
    boolean optAddressB = true;
    boolean optAddressC = true;
    boolean optAddressD = true;
    
    //city validation
    boolean cityValidation = true;
    
    //state validation
    boolean stateValidation = true; //false; you had it at false and never set it to true
    
    //country
    boolean countryValidation = true; //false; you had it at false and never set it to true
    
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
        
        
        //get the name os the customer
        String[] nameVerification = nameCreditCard.getText().split(" ");

        if(nameVerification.length<1){
            isName = false;
            JOptionPane.showMessageDialog(null,"Name should be entered");
        }else{
        
            if(nameVerification.length != 2){
                //error the name is of the format name_lastname_others 
                //which is wrong
                isName = false;
                JOptionPane.showMessageDialog(null,"Name should be in the format 'Name + Last Name'");
            }else{
            
                if(nameVerification[0].length()>=10 || nameVerification[1].length()>=10){
                
                    //error, the name should only contain less than 10 characters
                    isName = false;
                    JOptionPane.showMessageDialog(null,"first name has too many");
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
        
        //address verification
        
        String[] addressVerification = addressCreditCard.getText().split(" ");
        
        if(addressVerification.length<1){
            JOptionPane.showMessageDialog(null,"You must enter the address");
        }else{
        
            if(addressVerification.length != 4){
                //WRONG address format
                JOptionPane.showMessageDialog(null,"Wrong Address Format");

            }else{
                
                //JOptionPane.showMessageDialog(null,"hey");
                
                char stringa = addressVerification[0].charAt(0);
                char stringb = addressVerification[1].charAt(0);
                char stringc = addressVerification[2].charAt(0);
                char stringd = addressVerification[3].charAt(0);
                
                //JOptionPane.showMessageDialog(null,stringa + " " + stringb + " "+ stringc + " " +
                  //      stringd);
                  String test = "~ ! @ # $ % ^ & * ( ) _ + - = `";
                  
                if(addressVerification[0].length()<=5 && addressVerification[1].length()<=2
                    && addressVerification[2].length()<=5 &&
                   addressVerification[3].length()<=2){
                    
                    for(int i=0;i<addressVerification[0].length();i++){
                        
                        int h = test.indexOf(addressVerification[0].charAt(i));
                        
                        if(h != -1){
                            JOptionPane.showMessageDialog(null,"No Symbols");
                            controlA = false;
                            break;
                        }else{
                            if(!Character.isDigit(stringa)){
                                controlA = false;
                                break;
                            }
                        }                 
                    } //end of for 1

                    for(int i=0;i<addressVerification[1].length();i++){
                        
                        int h = test.indexOf(addressVerification[1].charAt(i));
                        
                        if(h != -1){
                            JOptionPane.showMessageDialog(null,"No Symbols");
                            controlB = false;
                            break;
                        }else{
                            
                            if(!Character.isLetter(stringb)){
                                controlB = false;
                                break;
                            }
                        }                 
                    } //end of for 2

                    for(int i=0;i<addressVerification[2].length();i++){
                        
                        int h = test.indexOf(addressVerification[2].charAt(i));
                        
                        if(h != -1){
                            JOptionPane.showMessageDialog(null,"No Symbols");
                            controlC = false;
                            break;
                        }else{
                            if((!Character.isDigit(stringc) && !Character.isLetter(stringc))){
                                controlC = false;
                                break;
                            }
                        }                 
                    } //end of for 3
                    
                    for(int i=0;i<addressVerification[3].length();i++){
                        
                        int h = test.indexOf(addressVerification[3].charAt(i));
                        
                        if(h != -1){
                            JOptionPane.showMessageDialog(null,"No Symbols");
                            controlD = false;
                            break;
                        }else{
                            
                            if(!Character.isLetter(stringd)){
                                controlD = false;
                                break;
                            }
                        }                 
                    } //end of for 4
                    
                    if(controlA && controlB && controlC && controlD){
                        JOptionPane.showMessageDialog(null,"good address");
                    }else{
                        JOptionPane.showMessageDialog(null,"Wrong Address Format, try again");
                    }
                }else{
                    //error too long address
                    JOptionPane.showMessageDialog(null,"Error, address is too long/not good format");
                    controlA = false;
                    controlB = false;
                    controlC = false;
                    controlD = false;
                }
            } //end of else (small)
        } //end of else (big)(length<1) (address)
        
        
        //address (optional)
        String[] addressOptVerification = adress2CreditCard.getText().split(" ");
        
            if(addressOptVerification.length != 4){
                //WRONG address format
                
                if(addressOptVerification.length>=1 && !addressOptVerification[0].equalsIgnoreCase("")){
 
                    //set to false
                    optAddressA = false;
                    optAddressB = false;
                    optAddressC = false;
                    optAddressD = false;
                    
                    JOptionPane.showMessageDialog(null,"Wrong Optional Address Formatttttt");
                    //JOptionPane.showMessageDialog(null,addressOptVerification.length);
                }
            }else{
              
                char stringa = addressOptVerification[0].charAt(0);
                char stringb = addressOptVerification[1].charAt(0);
                char stringc = addressOptVerification[2].charAt(0);
                char stringd = addressOptVerification[3].charAt(0);
                
                String test = "~ ! @ # $ % ^ & * ( ) _ + - = `";
                  
                if(addressOptVerification[0].length()<=5 && addressOptVerification[1].length()<=2
                    && addressOptVerification[2].length()<=5 &&
                   addressOptVerification[3].length()<=2){
                    
                    for(int i=0;i<addressOptVerification[0].length();i++){
                        
                        int h = test.indexOf(addressOptVerification[0].charAt(i));
                        
                        if(h != -1){
                            JOptionPane.showMessageDialog(null,"No Symbols In Address Optional");
                            optAddressA = false;
                            break;
                        }else{
                            if(!Character.isDigit(stringa)){
                                optAddressA = false;
                                break;
                            }
                        }                 
                    } //end of for 1

                    for(int i=0;i<addressOptVerification[1].length();i++){
                        
                        int h = test.indexOf(addressOptVerification[1].charAt(i));
                        JOptionPane.showMessageDialog(null,"testing h " + h);
                        
                        if(h != -1){
                            JOptionPane.showMessageDialog(null,"No Symbols");
                            optAddressB = false;
                            break;
                        }else{
                            if(!Character.isLetter(stringb)){
                                optAddressB = false;
                                break;
                            }
                        }                 
                    } //end of for 2

                    for(int i=0;i<addressOptVerification[2].length();i++){
                        
                        int h = test.indexOf(addressOptVerification[2].charAt(i));
                        
                        if(h != -1){
                            JOptionPane.showMessageDialog(null,"No Symbols");
                            optAddressC = false;
                            break;
                        }else{
                            if((!Character.isDigit(stringc) && !Character.isLetter(stringc))){
                                optAddressC = false;
                                break;
                            }
                        }                 
                    } //end of for 3
                    
                    for(int i=0;i<addressOptVerification[3].length();i++){
                        
                        int h = test.indexOf(addressOptVerification[3].charAt(i));
                        
                        if(h != -1){
                            JOptionPane.showMessageDialog(null,"No Symbols");
                            optAddressD = false;
                            break;
                        }else{
                            
                            if(!Character.isLetter(stringd)){
                                optAddressD = false;
                                break;
                            }
                        }                 
                    } //end of for 4
                    
                    if(optAddressA && optAddressB && optAddressC && optAddressD){
                        JOptionPane.showMessageDialog(null,"good optional address");
                    }else{
                        JOptionPane.showMessageDialog(null,"Wrong optional Address Format");
                        optAddressA = false;
                        optAddressB = false;
                        optAddressC = false;
                        optAddressD = false;
                    }
                }else{
                    //error too long address
                    JOptionPane.showMessageDialog(null,"Error, optional address is too long/not good format");
                    optAddressA = false;
                    optAddressB = false;
                    optAddressC = false;
                    optAddressD = false;
                }
        } //end of else (big)(length != 4) (optional address)
        
        //city validation
        String[] cityVerification = cityCreditCard.getText().split(" ");
        
        if(cityVerification.length != 1){
            //WRONG city format
            if(cityVerification[0].equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"This field is required");
            }else{
                JOptionPane.showMessageDialog(null,"Wrong format");
            }
            cityValidation = false;
        }else{
            
            if(cityVerification[0].length()>=1 && cityVerification[0].length()<=10){
                
                for(int i=0;i<cityVerification[0].length();i++){
                    
                    char temp = cityVerification[0].charAt(i);
                    //JOptionPane.showMessageDialog(null,"this is temp: " + temp);
                    
                    if(!Character.isLetter(temp)){
                        //error city have either numbers or symbols
                        JOptionPane.showMessageDialog(null,"city has symbols/numbers");
                        cityValidation = false;
                    }                 
                } //end of for
            }
        } //end of else (city)
        
        String[] stateVerification = stateCreditCard.getText().split(" ");
        
        if(stateVerification.length <1){
           
                JOptionPane.showMessageDialog(null,"This field is required");
                stateValidation = false;
        }else{
            
                if(stateVerification.length==1){
                    
                    if(stateVerification[0].length()==2){
                
                        for(int i=0;i<stateVerification[0].length();i++){
                    
                            char temp = stateVerification[0].charAt(i);
                    
                            if(!Character.isLetter(temp)){
                                //error state have either numbers or symbols
                                JOptionPane.showMessageDialog(null,"state has symbols/numbers");
                                stateValidation = false;
                            }   
                        } //end of for
                    }else{   
                        JOptionPane.showMessageDialog(null,"too many characters");
                        stateValidation = false;
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"state has too many letters");
                }
            }
        
 
        
        //country
        String[] countryVerification = countryCreditCard.getText().split(" ");
        
        if((countryVerification.length != 2 && countryVerification.length != 1)){
           //error it's in the wrong format
           JOptionPane.showMessageDialog(null,"Wrong Country format");
           countryValidation = false;
        }else{
        
            for(int i=0;i<countryVerification.length;i++){
                
                if(countryVerification[i].length()>=10){
                    //error, the country should only contain less than 10 characters
                    JOptionPane.showMessageDialog(null,"country has too many letters");
                    countryValidation = false;
                }else{
                
                    String temp = countryVerification[i];
                    
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
        } //end of else (big)
        
        //credit card verification
        
        //variables (tools)
	String creditCardNumber = cardNumber.getText();
	Integer[] numbers = new Integer[creditCardNumber.length()];
	int total_numbers = 0;
	boolean isTrue= true;
		
	if(creditCardNumber.length()!=16){
            JOptionPane.showMessageDialog(null, "Sorry, Card is NOT valid");
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
            }
        } //end of else(big)
        
        
        /*if(isName && ccvValidation && controlA && controlB && controlC && controlD && optAddressA
                && optAddressB && optAddressC && optAddressD && cityValidation && stateValidation 
                && countryValidation){*/
        
        //Roger Boza
        if(isName && ccvValidation && controlA && controlB && controlC && controlD
                && cityValidation && stateValidation && countryValidation){

            if(btnAction.getText().equalsIgnoreCase("update")){//update card  
                Address billing = new Address(nameCreditCard.getText(), addressCreditCard.getText(), adress2CreditCard.getText(), cityCreditCard.getText(), stateCreditCard.getText(), 0, countryCreditCard.getText());
                CreditCard newCard = new CreditCard(activeCard.getcCID(), nameCreditCard.getText(), creditCardNumber, monthToNumber(comboMonth.getValue()) + "/" + comboYear.getValue(), ccvCredit.getText(), billing);
                newCard.updateCardInDB();

                Bookfy.getUser().loadCreditCards();
                Bookfy.getMainWindowController().displayViewProfile();
            }
            else{//save card
                Address billing = new Address(nameCreditCard.getText(), addressCreditCard.getText(), adress2CreditCard.getText(), cityCreditCard.getText(), stateCreditCard.getText(), 0, countryCreditCard.getText());
                CreditCard newCard = new CreditCard(0, nameCreditCard.getText(), creditCardNumber, monthToNumber(comboMonth.getValue()) + "/" + comboYear.getValue(), ccvCredit.getText(), billing);
                newCard.saveCardIntoDB();

                Bookfy.getUser().loadCreditCards();
                Bookfy.getMainWindowController().displayViewProfile();
            }//end Roger Boza
            
        }
  
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