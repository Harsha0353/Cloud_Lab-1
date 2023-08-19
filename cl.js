document.getElementById("healthcareForm").addEventListener("submit", function(event) 
    {
        event.preventDefault();
        
        const patientName = document.getElementById("patientName").value;
        const age = document.getElementById("age").value;
        const diagnosis = document.getElementById("diagnosis").value;
        
        const xmlString = `
            <healthcare>
                <patient>
                    <name>${patientName}</name>
                    <age>${age}</age>
                    <diagnosis>${diagnosis}</diagnosis>
                </patient>
            </healthcare>
        `;
        
        const blob = new Blob([xmlString], { type: "application/xml" });
        const url = URL.createObjectURL(blob);
        
        const a = document.createElement("a");
        a.href = url;
        a.download = "healthcare_data.xml";
        a.click();
    });
