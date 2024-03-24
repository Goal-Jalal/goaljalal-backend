# goaljalal-backend

### GitFlow

> Describes JIRA-based gitflow

1. Create a JIRA ticket before you start working.

2. One ticket should preferably be a single commit.

3. Keep the commit graph as simple as possible.

4. Don't change the revision history of branches you share with each other.

5. Make sure to get reviews from reviewers.
6. merge your own pull requests.

```mermaid
gitGraph
    commit
    branch GJ-01
    checkout GJ-01
    commit
    commit
    checkout main
    merge GJ-01
    branch GJ-02
    checkout GJ-02
    commit
    commit
    checkout main
    merge GJ-02
    branch GJ-03
    checkout GJ-03
    commit
    commit
    checkout main
    merge GJ-03
```