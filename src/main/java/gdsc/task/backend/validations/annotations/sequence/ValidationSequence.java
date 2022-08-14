package gdsc.task.backend.validations.annotations.sequence;

import gdsc.task.backend.validations.annotations.groups.*;

import javax.validation.GroupSequence;

@GroupSequence(value={
        NotNullGroup.class,
        NotEmptyGroup.class,
        NotBlankGroup.class,
        LengthGroup.class,
        DBConstraints.class
})

public interface ValidationSequence {
}
