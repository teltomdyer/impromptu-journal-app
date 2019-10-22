# Impromptu Journal Schema

### Overall Schema
```
Entry (entry_id, entry_content, latitude, longitude, timestamp, prompt_id, user_id)
	Foreign Key: prompt_id references Prompt
    Foreign Key: user_id references User
User (user_id, username, first_name, last_name, password, display_prompts)
Prompt (prompt_id, prompt_content)
```


### Tables
#### Entry
``` 
    entry_id PRIMARY_KEY    id of journal entry
    entry_content           content of journal entry
    latitude                latitude of location when entry made
    longitude               longitude of location when entry made
    timestamp               time entry was created
    prompt_id               id of prompt that prompted the entry
    user_id                 id of user who created the entry
    
    FOREIGN_KEY prompt_id --> Prompts
    FOREIGN_KEY user_id --> Users
```

#### User 
``` 
    user_id PRIMARY_KEY     id of user
    username                name of user
    first_name              first name of user
    last_name               last name of user
    password                password for the user
    display_prompts         preference of whether to display journal prompts
```

#### Prompt 
``` 
    prompt_id PRIMARY_KEY   id of prompt
    prompt_content          content of prompt
```

### Table Name Explanation/Relationship of Entities/Normalization 
``` 
    Table Names Explained:
    User       Simply called this because the subject of the table is the users 
    Entry      The subject of this table is the entries the users have written.
    Prompt     The subject of this entry is the prompts that can be used for an entry.
    
    The tables are related in the following way:
        Entries have foreign keys to both the Prompt table and the User table. In this way, a user can be joined with their associated entry to link those two tables together. The Entry table can then be joined with the prompt table to gather the content of the prompt and link it back to the user. 
        
    Normalization
        The relations above are in 1st and 2nd normal form. The columns are atomic in every relation, and they all rely upon the primary key. They are also in 3rd and 4th normal form because there are no other functional dependencies besides the primary keys to the rest of their attributes and no multi-valued dependencies.
        
```

