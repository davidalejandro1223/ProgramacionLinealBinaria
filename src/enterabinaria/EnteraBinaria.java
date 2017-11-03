package enterabinaria;

//import scpsolver.constraints.LinearBiggerThanEqualsConstraint;
//import scpsolver.constraints.LinearSmallerThanEqualsConstraint;
//import scpsolver.lpsolver.LinearProgramSolver;
//import scpsolver.lpsolver.SolverFactory;
import scpsolver.problems.LPSolution;
import scpsolver.problems.LPWizard;
//import scpsolver.problems.LinearProgram;

public class EnteraBinaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LPWizard lpw = new LPWizard();
        lpw.plus("x1", 20).plus("x2", 40).plus("x3", 20).plus("x4", 15).plus("x5", 30);
        lpw.addConstraint("c1", 25, ">=").plus("x1", 5).plus("x2", 4).plus("x3", 3).plus("x4", 7).plus("x5", 8).setAllVariablesBoolean();
        lpw.addConstraint("c2", 25, ">=").plus("x1", 1).plus("x2", 7).plus("x3", 9).plus("x4", 4).plus("x5", 6).setAllVariablesBoolean();
        lpw.addConstraint("c3", 25, ">=").plus("x1", 8).plus("x2", 10).plus("x3", 2).plus("x4", 1).plus("x5", 10).setAllVariablesBoolean();
        lpw.setMinProblem(false);
        LPSolution sol = lpw.solve();
        //LPSolution sol = lpw.solve(SolverFactory.newDefault());
        System.out.println(sol);
        
    }

}
