package enterabinaria;

//import scpsolver.constraints.LinearBiggerThanEqualsConstraint;
//import scpsolver.constraints.LinearSmallerThanEqualsConstraint;
//import scpsolver.lpsolver.LinearProgramSolver;
//import scpsolver.lpsolver.SolverFactory;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import javax.swing.JTable;
import scpsolver.problems.LPSolution;
import scpsolver.problems.LPWizard;
import scpsolver.problems.LPWizardConstraint;
//import scpsolver.problems.LinearProgram;

public class EnteraBinaria {

    private LPWizard lpw;
    private boolean minProblem;
    private int numVar;
    private int numRes;
    boolean confIniComplete = false;

    public EnteraBinaria() {
        this.lpw = new LPWizard();
    }

    public void setMinProblem(boolean minProblem) {
        this.minProblem = minProblem;
    }

    public void setNumVar(int numVar) {
        this.numVar = numVar;
    }

    public void setNumRes(int numRes) {
        this.numRes = numRes;
    }

    public String Solucionar(JTable tabla) {
        lpw.setMinProblem(minProblem);
        for (int i = 0; i <= numRes; i++) {
            LPWizardConstraint lpwc = null;
            for (int j = 1; j <= numVar; j++) {
                if (i == 0 && j <= numVar) {
                    this.lpw.plus("x" + j, Integer.parseInt(tabla.getValueAt(i, j).toString()));
                } else if(i!=0){
                    int val;
                    if (!this.confIniComplete) {
                        val = Integer.parseInt(tabla.getValueAt(i, numVar + 2).toString());
                        String op = tabla.getValueAt(i, numVar + 1).toString();
                        if(op.compareTo("<=")==0){
                            op = ">=";
                        }else if(op.compareTo(">=")==0){
                            op = "<=";
                        }else if(op.compareTo(">")==0){
                            op = "<";
                        }else{
                            op = ">";
                        }
                        lpwc = this.lpw.addConstraint("c"+i, val, op);
                        this.confIniComplete = true;
                        j = j-1;
                    } else {
                        try{
                            val = Integer.parseInt(tabla.getValueAt(i, j).toString());
                            lpwc.plus("x"+j, val);
                        }catch(NumberFormatException nfe){
                            System.out.println(nfe.getMessage());
                        }
                    }
                }
            }
            if(lpwc != null){
                lpwc.setAllVariablesBoolean();
                this.confIniComplete = false;
            }           
        }
        
        LPSolution sol = lpw.solve();
        return sol.toString();
    }

    public static void main(String[] args) {
        LPWizard lpw = new LPWizard();
        lpw.plus("x1", 20).plus("x2", 40).plus("x3", 20).plus("x4", 15).plus("x5", 30);
        lpw.addConstraint("c1", 25, ">=").plus("x1", 5).plus("x2", 4).plus("x3", 3).plus("x4", 7).plus("x5", 8).setAllVariablesBoolean();
        lpw.addConstraint("c2", 25, ">=").plus("x1", 1).plus("x2", 7).plus("x3", 9).plus("x4", 4).plus("x5", 6).setAllVariablesBoolean();
        lpw.addConstraint("c3", 25, ">=").plus("x1", 8).plus("x2", 10).plus("x3", 2).plus("x4", 1).plus("x5", 10).setAllVariablesBoolean();
        lpw.setMinProblem(false);
        LPSolution sol = lpw.solve();
        System.out.println(sol.toString());

    }

}
