import React from 'react';
import Typography from '@mui/material/Typography';
import { Box, Button, ButtonGroup, FormControl } from '@mui/material';
import { MenuItem, InputLabel, Select } from '@mui/material';
import { Add } from '@mui/icons-material';

import { Grid, Container } from '@mui/material';
import BottomSheet from 'components/BottomSheet/BottomSheet';

import './ProfileHeader.scss';

function ProfileHeader(props) {
  const [open, setOpen] = React.useState(false);

  let c = 0;

  const handleOpen = () => {
    setOpen(true);
  };

  return (
    <div className="component-profile-header">

      {/* header */}
      <Grid container spacing={2} sx={{ mb: 2 }}>

        <Grid item xs={props.layout[c++]} sx={{ display: 'flex', alignItems: 'center' }}>
          <Typography variant='h5'>{props.title}</Typography>
        </Grid>

        {props.buttonGroup &&
          <Grid item xs={props.layout[c++]} sx={{ display: 'flex', justifyContent: 'end', alignItems: 'center' }}>
            <ButtonGroup variant="text" aria-label="text button group">
              {props.buttonGroup.titles.map(title =>
                <Button onClick={props.buttonGroup.handleClick}>{title}</Button>
              )}
            </ButtonGroup>
          </Grid>
        }
        {!props.noAction &&
          <Grid item xs={props.layout[c++]} sx={{ display: 'flex', justifyContent: 'end' }}>
            <Button variant="outlined" startIcon={<Add />} onClick={handleOpen}>
              Add {props.actionButton}
            </Button>
          </Grid>
        }
        {props.dropdownData &&
          <Grid item xs={props.layout[c++]}>
            <FormControl fullWidth>
              <InputLabel size='small' id="select-label-class">{props.dropdownData.title}</InputLabel>
              <Select
                size='small'
                labelId="select-label-class"
                value={props.dropdownData.className}
                label="Age"
                onChange={props.dropdownData.handleSelect}
              >
                {props.dropdownData.data.map(dropdownInfo =>
                  <MenuItem value={dropdownInfo.id}>{dropdownInfo.className}</MenuItem>
                )}
              </Select>
            </FormControl>
          </Grid>
        }
      </Grid>

      {props.bottomSheetTitle &&
        <BottomSheet title={props.bottomSheetTitle} open={open} onClose={() => setOpen(false)} onSave={props.bottomSheetOnSave}>
          {props.children}
        </BottomSheet>
      }

    </div>
  );
}

export default ProfileHeader;
