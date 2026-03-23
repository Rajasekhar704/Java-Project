import java.util.*;

public class CollegeVoting{
    static Scanner sc = new Scanner(System.in);

    static class Candidate {
        int id;
        String name;
        int votes;

        Candidate(int id, String name) {
            this.id = id;
            this.name = name;
            this.votes = 0;
        }
    }

    static ArrayList<Candidate> candidates = new ArrayList<>();
    static HashSet<String> votedVoters = new HashSet<>(); // Stores voter IDs who already voted

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("------ COLLEGE ONLINE VOTING SYSTEM ------");
            System.out.println("1. Add Candidate");
            System.out.println("2. View Candidates");
            System.out.println("3. Cast Vote");
            System.out.println("4. View Results");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCandidate();
                    break;
                case 2:
                    viewCandidates();
                    break;
                case 3:
                    try {
                        castVote();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    viewResults();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
            System.out.println();
        } while (choice != 5);
    }

    static void addCandidate() {
        System.out.print("Enter Candidate ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Candidate Name: ");
        String name = sc.nextLine();

        candidates.add(new Candidate(id, name));
        System.out.println("Candidate added successfully!");
    }

    static void viewCandidates() {
        if (candidates.isEmpty()) {
            System.out.println("No candidates found!");
            return;
        }
        System.out.println("------ Candidate List ------");
        for (Candidate c : candidates) {
            System.out.println("ID: " + c.id + " | Name: " + c.name + " | Votes: " + c.votes);
        }
    }

    static void castVote() throws Exception {
        if (candidates.isEmpty()) {
            throw new Exception("No candidates available to vote for!");
        }

        System.out.print("Enter your Voter ID (e.g., Student Roll No): ");
        String voterId = sc.nextLine();

        // Check if the voter already voted
        if (votedVoters.contains(voterId)) {
            throw new Exception("You have already voted! Only one vote is allowed per voter.");
        }

        System.out.println("------ Vote for Your Candidate ------");
        for (Candidate c : candidates) {
            System.out.println(c.id + ". " + c.name);
        }

        System.out.print("Enter Candidate ID to vote: ");
        int id = sc.nextInt();

        boolean found = false;
        for (Candidate c : candidates) {
            if (c.id == id) {
                c.votes++;
                votedVoters.add(voterId); // mark voter as voted
                System.out.println("✅ You voted for " + c.name + "!");
                found = true;
                break;
            }
        }

        if (!found) {
            throw new Exception("Invalid Candidate ID! Please try again.");
        }
    }

    static void viewResults() {
        if (candidates.isEmpty()) {
            System.out.println("No results to display!");
            return;
        }

        System.out.println("------ Voting Results ------");
        for (Candidate c : candidates) {
            System.out.println("Candidate: " + c.name + " | Votes: " + c.votes);
        }

        Candidate winner = Collections.max(candidates, Comparator.comparingInt(c -> c.votes));
        System.out.println("-----------------------------------");
        System.out.println("🏆 Winner: " + winner.name + " with " + winner.votes + " votes!");
    }
}
