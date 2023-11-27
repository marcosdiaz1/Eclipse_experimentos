/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import clases.Cliente;
import clases.Factura;
import clases.Producto;
import clases.Vendedor;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class Autocomplete implements DocumentListener {

  public static enum Mode {
    INSERT,
    COMPLETION
  };

  private JTextField textField;
  private Object lstMatches;
  private Object match;
  private String target;
  private Mode mode = Mode.INSERT;

  public Autocomplete(JTextField textField, Object lstMatches, String target) {
    this.textField = textField;
    this.lstMatches = lstMatches;
    this.target = target;
  }
  
  public Mode getMode(){
      return mode;
  }
  
  public Object getMatch(){
      return match;
  }

  @Override
  public void changedUpdate(DocumentEvent ev) { 
  }

  @Override
  public void removeUpdate(DocumentEvent ev) { 
  }
  
  private List<String> getKeyWords(){
      List<String> keyWords = new ArrayList<>();
      switch(target){
            case "vendedores":
                for (Object match : (ArrayList<Vendedor>)lstMatches) {
                    keyWords.add(((Vendedor) match).getNombres() + " " + ((Vendedor) match).getApellidos());
                }
                break;
            case "productos":
                for (Object match : (ArrayList<Producto>)lstMatches) {
                    keyWords.add(((Producto) match).getDescripcion());
                }
                break;
            case "clientes":
                for (Object match : (ArrayList<Cliente>)lstMatches) {
                    keyWords.add(((Cliente) match).getNombres() + " " + ((Cliente) match).getApellidos());
                }
                break;
        }
      return keyWords;
  }

  @Override
  public void insertUpdate(DocumentEvent ev) {
    if (ev.getLength() != 1)
      return;

    int pos = ev.getOffset();
    String content = null;
    try {
      content = textField.getText(0, pos + 1);
    } catch (BadLocationException e) {
      System.out.println(e.getMessage());
    }
    // Find where the word starts
    int w;
    for (w = pos; w >= 0; w--) {
      if (!(Character.isLetter(content.charAt(w)) || Character.isDigit(content.charAt(w)))) {
        break;
      }
    }

    String prefix = content.substring(w + 1);
    
    List<String> keywords = getKeyWords();
    int n = keywords.indexOf(prefix);
        
    if(prefix.isEmpty())
        n = 0;
    
    boolean matchedWord = false;
      for (int i = 0; i < keywords.size(); i++) {
        if(keywords.get(i).startsWith(prefix)){
            matchedWord = true;
            match = ((ArrayList<Object>)lstMatches).get(i);
            // A completion is found
          String completion = keywords.get(i).substring(pos - w);

          // We cannot modify Document from within notification,
          // so we submit a task that does the change later
          SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
          break;
        }          
      }
    if(matchedWord){
        mode = Mode.COMPLETION;
    }
    else {
      // Nothing found
      mode = Mode.INSERT;
    }
  }

  public class CommitAction extends AbstractAction {

    private static final long serialVersionUID = 5794543109646743416L;

    @Override
    public void actionPerformed(ActionEvent ev) {
      if (mode == Mode.COMPLETION) {
        int pos = textField.getSelectionEnd();
        StringBuffer sb = new StringBuffer(textField.getText());
        sb.insert(pos, "");
        textField.setText(sb.toString());
        textField.setCaretPosition(pos);
      } 
      else {
        textField.replaceSelection("\t");
      }
    }
  }

  private class CompletionTask implements Runnable {
    private String completion;
    private int position;

    CompletionTask(String completion, int position) {
      this.completion = completion;
      this.position = position;
    }

    public void run() {
      StringBuffer sb = new StringBuffer(textField.getText());
      sb.insert(position, completion);
      textField.setText(sb.toString());
      textField.setCaretPosition(position + completion.length());
      textField.moveCaretPosition(position);
      mode = Mode.COMPLETION;
    }
  }

}