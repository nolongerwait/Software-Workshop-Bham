public class UniqueList {
    public static List<Integer> unique(List<Integer> a, List<Integer> b) {
        if(a.isEmpty()) return b;
        else if(b.isEmpty()) return a;
        else {
            if(a.getHead().compareTo(b.getHead()) == -1) {
                return new List<Integer>(a.getHead(), unique(a.getTail(), b));
            }
            else if(a.getHead().compareTo(b.getHead()) == 1) {
                return new List<Integer>(b.getHead(), unique(a, b.getTail()));
            }
            else {
                return unique(a.getTail(), b.getTail());
            }
        }
    }
}